package com.zerobase.user.jwt;

import static com.zerobase.user.dto.response.BasicErrorCode.CREATE_TOKEN_ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.DEACTIVATED_USER_ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.INTERNAL_SERVER_ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.SUSPENDED_USER_ERROR;
import static com.zerobase.user.dto.response.ValidErrorCode.LOGIN_FAIL_ERROR;
import static com.zerobase.user.dto.response.ValidErrorCode.USER_NOT_FOUND_ERROR;
import static com.zerobase.user.type.UserStatus.DEACTIVATED;
import static com.zerobase.user.type.UserStatus.INACTIVE;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.user.dto.request.LoginRequestDTO;
import com.zerobase.user.dto.response.LoginSuccessDTO;
import com.zerobase.user.entity.ProfileEntity;
import com.zerobase.user.entity.RefreshEntity;
import com.zerobase.user.entity.UserEntity;
import com.zerobase.user.exception.BizException;
import com.zerobase.user.repository.ProfileRepository;
import com.zerobase.user.repository.RefreshRepository;
import com.zerobase.user.repository.UserRepository;
import com.zerobase.user.util.CookieUtil;
import com.zerobase.user.util.JWTUtil;
import com.zerobase.user.util.ResponseUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Logger 적용으로 로깅 개선
@Slf4j
@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    //JWTUtil 주입
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final CookieUtil cookieUtil;

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // JSON 형태로 받은 요청에서 email password를 추출하는 방법입니다.
        // JSON 데이터를 처리할 DTO 클래스
        LoginRequestDTO loginRequest = null;
        try {
            // JSON 데이터를 LoginRequest DTO로 변환
            loginRequest = new ObjectMapper().readValue(request.getInputStream(),
                LoginRequestDTO.class);
            log.debug("Login attempt for user with email: {}", loginRequest.getEmail());
        } catch (IOException e) {
            log.error("Failed to parse login request", e);
            throw new RuntimeException(e);
        }

        // 추출된 username과 password 사용
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        log.debug("Attempting to authenticate user with email: {}", email);

        // 사용자 정보 조회 및 상태 확인
        UserEntity userEntity = userRepository.findByEmail(email)
            .orElseThrow(() -> new BizException(USER_NOT_FOUND_ERROR));

        if (DEACTIVATED.equals(userEntity.getStatus())) {
            ResponseUtil.setJsonResponse(response, FORBIDDEN.value(), DEACTIVATED_USER_ERROR);
        } else if (INACTIVE.equals(userEntity.getStatus())) {
            ResponseUtil.setJsonResponse(response, FORBIDDEN.value(), SUSPENDED_USER_ERROR);
        }


        //스프링 시큐리티에서 email과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            email, password, null);
        log.debug("Generated authentication token for user: {}", email);

        //token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);
    }

    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, FilterChain chain, Authentication authentication) {
        //유저 정보
        String email = authentication.getName();
        log.info("Authentication successful for user: {}", email);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        UserEntity userEntity = userRepository.findByEmail(email)
            .orElseThrow(() -> new BizException(USER_NOT_FOUND_ERROR));
        Long userEntityId = userEntity.getId();

        try {
            //토큰 생성
            String access = jwtUtil.createJwt("access", email, role, 1800000L, userEntityId);
            String refresh = jwtUtil.createJwt("refresh", email, role, 86400000L, userEntityId);
            log.info("Generated access and refresh tokens for user: {}", email);

            //Refresh 토큰 저장
            addRefreshEntity(email, refresh, 86400000L);

            //응답 설정
            response.setHeader("access", access);
            response.addCookie(cookieUtil.createCookie("refresh", refresh));

            // JSON 응답 생성

            Optional<ProfileEntity> OptionalProfileEntity = profileRepository.findByUserId(
                userEntityId);

            LoginSuccessDTO loginSuccessDTO;

            loginSuccessDTO = LoginSuccessDTO.builder()
                .Id(userEntityId)
                .profileCheck(OptionalProfileEntity.isPresent())
                .build();

            ResponseUtil.setJsonResponse(response, HttpServletResponse.SC_OK, loginSuccessDTO);
        } catch (JwtException e) { // jwtUtil.createJwt(...) 에서 발생할 수 있는 예외
            log.error("Failed to create JWT token", e);
            throw new BizException(CREATE_TOKEN_ERROR);
        } catch (Exception e) { // 기타 모든 예외
            log.error("Unexpected error during authentication", e);
            throw new BizException(INTERNAL_SERVER_ERROR);
        }

        log.debug("Access and refresh tokens sent in response for user: {}", email);
    }

    private void addRefreshEntity(String email, String refresh, Long expiredMs) {

        // 만료일자
        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshEntity refreshEntity = RefreshEntity.builder()
            .email(email)
            .refresh(refresh)
            .expiration(date.toString())
            .build();

        refreshRepository.save(refreshEntity);
    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, AuthenticationException failed) {

        log.warn("Authentication failed for user: {}", request.getParameter("email"), failed);

        // 로그인 실패 시 401 응답 코드 반환

        // JSON 응답 생성
        ResponseUtil.setJsonResponse(response, SC_UNAUTHORIZED,
            LOGIN_FAIL_ERROR);

    }
}

