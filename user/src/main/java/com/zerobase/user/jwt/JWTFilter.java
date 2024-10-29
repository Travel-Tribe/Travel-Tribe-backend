package com.zerobase.user.jwt;

import com.zerobase.user.entity.UserEntity;
import com.zerobase.user.repository.UserRepository;
import com.zerobase.user.type.Role;
import com.zerobase.user.util.JWTUtil;
import com.zerobase.user.util.ResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 헤더에서 access키에 담긴 토큰을 꺼냄
        String accessToken = request.getHeader("access");

        // 토큰이 없다면 다음 필터로 넘김
        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {
            ResponseUtil.setJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 토큰이 access인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(accessToken);

        if (!category.equals("access")) {
            ResponseUtil.setJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //토큰에서 username과 role 획득
        String email = jwtUtil.getEmail(accessToken);
        String roleString = jwtUtil.getRole(accessToken);

        Role role;
        try {
            // roleString을 Role enum으로 변환
            role = Role.fromString(roleString); // 변환 시 예외 처리
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role");
            filterChain.doFilter(request, response);
            return; // 필터 체인 종료
        }

        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(email);
        if (optionalUserEntity.isEmpty()) {
            filterChain.doFilter(request, response);
            return; // 필터 체인 종료
        }

        //userEntity를 생성하여 값 set
        UserEntity userEntity = UserEntity.builder()
            .username(optionalUserEntity.get().getUsername())
            .password(optionalUserEntity.get().getPassword())
            .email(email)
            .role(role)
            .build();   // createDate는 자동으로 설정됨

        //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);

    }
}
