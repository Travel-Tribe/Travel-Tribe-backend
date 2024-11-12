package com.zerobase.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        http
            .csrf(ServerHttpSecurity.CsrfSpec::disable) // CSRF 보호 비활성화
            .authorizeExchange(exchange -> exchange
                .pathMatchers("/logout").permitAll() // /logout 경로 허용
                .anyExchange().permitAll() // 모든 요청 허용
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // 로그아웃 경로 설정
                .logoutSuccessHandler((exchange, authentication) -> {
                    // 로그아웃 성공 시 처리 로직 (필요할 경우)
                    return exchange.getExchange().getResponse().setComplete();
                })
            );

        return http.build();
    }
}
