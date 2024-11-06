package com.zerobase.gateway.jwt;

import com.zerobase.gateway.util.JWTUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtGatewayFilterFactory extends
    AbstractGatewayFilterFactory<JwtGatewayFilterFactory.Config> {

    private final JWTUtil jwtUtil;

    @Autowired
    public JwtGatewayFilterFactory(JWTUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String accessToken = exchange.getRequest().getHeaders().getFirst("access");

            if (accessToken == null) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            try {
                // 토큰 검증
                jwtUtil.validateToken(accessToken);

                // 이메일과 역할 정보를 추출하여 헤더에 추가
                String email = jwtUtil.getEmail(accessToken);
                String role = jwtUtil.getRole(accessToken);
                String userId = jwtUtil.getUserId(accessToken);

                // 기존 헤더를 복사하고 새로운 헤더를 추가
                HttpHeaders headers = new HttpHeaders();
                headers.putAll(exchange.getRequest().getHeaders());
                headers.add("X-User-Email", email);
                headers.add("X-User-Role", role);
                headers.add("X-User-Id", userId);

                // 새로운 ServerHttpRequestDecorator를 생성하여 헤더를 교체
                ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                    @Override
                    public HttpHeaders getHeaders() {
                        return headers;
                    }
                };

                // 변경된 요청으로 Exchange 생성
                ServerWebExchange mutatedExchange = exchange.mutate()
                    .request(mutatedRequest)
                    .build();

                return chain.filter(mutatedExchange);
            } catch (JwtException e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }

    public static class Config {
        // 필요한 설정이 있으면 추가
    }
}
