package com.zerobase.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {

    private final Key secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}") String secret) {
        // 알고리즘을 명시적으로 지정
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
            Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    // 토큰에서 이메일을 추출하는 메서드
    public String getEmail(String token) {
        Claims claims = parseToken(token);
        return claims.get("email", String.class);
    }

    public String getUserId(String token) {
        Claims claims = parseToken(token);
        return claims.get("userId", String.class);
    }

    // 토큰에서 역할(Role)을 추출하는 메서드
    public String getRole(String token) {
        Claims claims = parseToken(token);
        return claims.get("role", String.class);
    }

    // 토큰의 유효성을 검증하는 메서드 (예외를 던지는 방식)
    public void validateToken(String token) throws JwtException {
        parseToken(token);
    }

    // 내부적으로 토큰을 파싱하여 Claims를 얻는 메서드
    private Claims parseToken(String token) throws JwtException {
        return Jwts.parser().verifyWith((SecretKey) secretKey).build().parseSignedClaims(token).getPayload();
    }
}
