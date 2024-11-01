package com.zerobase.user.service;

import static com.zerobase.user.dto.response.BasicErrorCode.EXPIRED_TOKEN_ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.INVALID_REFRESH_TOKEN_CATEGORY_ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.REFRESH_TOKEN_NOT_FOUND_IN_COOKIE_ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.REFRESH_TOKEN_NOT_IN_DATABASE;
import static com.zerobase.user.dto.response.BasicErrorCode.TOKEN_VALIDATION_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.zerobase.user.entity.RefreshEntity;
import com.zerobase.user.exception.TokenException;
import com.zerobase.user.repository.RefreshRepository;
import com.zerobase.user.util.CookieUtil;
import com.zerobase.user.util.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReissueService {

    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;
    private final CookieUtil cookieUtil;

    public void reissue(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = getRefreshTokenFromCookies(request);

        if (refreshToken == null) {
            log.warn("Refresh token not found in cookies");
            throw new TokenException(REFRESH_TOKEN_NOT_FOUND_IN_COOKIE_ERROR);
        }

        // Refresh 토큰 검증
        validateRefreshToken(refreshToken);

        // 이메일과 역할을 토큰에서 추출
        String email = jwtUtil.getEmail(refreshToken);
        String role = jwtUtil.getRole(refreshToken);

        // 새로운 Access, Refresh 토큰 생성
        String newAccessToken = jwtUtil.createJwt("access", email, role, 600000L);  // 10분 만료
        String newRefreshToken = jwtUtil.createJwt("refresh", email, role, 86400000L);  // 1일 만료

        // DB에 새 Refresh 토큰 저장
        updateRefreshToken(email, refreshToken, newRefreshToken);

        // 새 토큰을 응답에 추가
        setTokensInResponse(response, newAccessToken, newRefreshToken);
    }

    private String getRefreshTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if ("refresh".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * Refresh 토큰을 DB에 갱신하는 메서드
     */
    private void updateRefreshToken(String email, String oldRefreshToken, String newRefreshToken) {
        // 기존 토큰 삭제 후 새 토큰 저장
        refreshRepository.deleteByRefresh(oldRefreshToken);
        addRefreshEntity(email, newRefreshToken, 86400000L);  // 1일 만료로 설정
    }

    /**
     * 새 토큰을 응답에 설정하는 메서드
     */
    private void setTokensInResponse(HttpServletResponse response, String newAccessToken,
        String newRefreshToken) {
        response.setHeader("access", newAccessToken);
        response.addCookie(cookieUtil.createCookie("refresh", newRefreshToken));
    }

    /**
     * 새 Refresh 토큰을 DB에 저장하는 메서드
     */
    private void addRefreshEntity(String email, String refresh, Long expiredMs) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiredMs);

        RefreshEntity refreshEntity = RefreshEntity.builder()
            .email(email)
            .refresh(refresh)
            .expiration(expirationDate.toString())
            .build();

        refreshRepository.save(refreshEntity);
    }

    /**
     * Refresh 토큰의 유효성을 검증하는 메서드
     */
    private void validateRefreshToken(String refreshToken) {
        try {
            // 토큰 만료 여부 체크
            if (jwtUtil.isExpired(refreshToken)) {
                log.warn("Expired refresh token: {}", refreshToken);
                throw new TokenException(EXPIRED_TOKEN_ERROR);
            }

            // 토큰이 "refresh" 카테고리인지 확인
            if (!"refresh".equals(jwtUtil.getCategory(refreshToken))) {
                log.warn("Token is not a refresh token: {}", refreshToken);
                throw new TokenException(INVALID_REFRESH_TOKEN_CATEGORY_ERROR, BAD_REQUEST);
            }

            // DB에 Refresh 토큰이 존재하는지 확인
            if (!refreshRepository.existsByRefresh(refreshToken)) {
                log.warn("Refresh token does not exist in DB: {}", refreshToken);
                throw new TokenException(REFRESH_TOKEN_NOT_IN_DATABASE);
            }
        } catch (ExpiredJwtException e) {
            log.warn("Expired refresh token: {}", refreshToken);
            throw new TokenException(EXPIRED_TOKEN_ERROR);
        } catch (Exception e) {
            log.error("Error validating refresh token: {}", refreshToken, e);
            throw new TokenException(TOKEN_VALIDATION_ERROR, INTERNAL_SERVER_ERROR);
        }
    }
}

