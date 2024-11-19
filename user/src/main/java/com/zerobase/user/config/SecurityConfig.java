package com.zerobase.user.config;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import com.zerobase.user.exception.CustomAuthenticationEntryPoint;
import com.zerobase.user.jwt.CustomLogoutFilter;
import com.zerobase.user.jwt.JWTFilter;
import com.zerobase.user.jwt.LoginFilter;
import com.zerobase.user.repository.ProfileRepository;
import com.zerobase.user.repository.RefreshRepository;
import com.zerobase.user.repository.UserRepository;
import com.zerobase.user.util.CookieUtil;
import com.zerobase.user.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    //JWTUtil 주입
    private final JWTUtil jwtUtil;
    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final RefreshRepository refreshRepository;
    private final CookieUtil cookieUtil;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint; // 추가된 부분

    //AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        http
//            .cors(
//                corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
//                    @Override
//                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                        CorsConfiguration configuration = new CorsConfiguration();
//
//                        configuration.setAllowedOrigins(
//                            Collections.singletonList("http://localhost:3000"));
//                        configuration.setAllowedMethods(Collections.singletonList("*"));
//                        configuration.setAllowCredentials(true);
//                        configuration.setAllowedHeaders(Collections.singletonList("*"));
//                        configuration.setMaxAge(3600L);
//                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));
//
//                        return configuration;  // CorsConfiguration 객체를 반환해야 함
//                    }
//                }));

        //csrf disable
        http
            .csrf((auth) -> auth.disable());

        //From 로그인 방식 disable
        http
            .formLogin((auth) -> auth.disable());

        //http basic 인증 방식 disable
        http
            .httpBasic((auth) -> auth.disable());

        // 수정된 예외 처리 구성
        http.exceptionHandling((exceptionHandling) -> exceptionHandling
            .authenticationEntryPoint(customAuthenticationEntryPoint));

        // 경로별 인가 작업
        // 경로별 접근 권한 설정
        // 비로그인 기능만 permitAll로 처리, 토큰이 넘어오지 않음.
        // SecurityContextHolder에 setAuthentication 설정을 안해줌.
        // 인증 실패가 발생!
        http.authorizeHttpRequests(auth -> auth
                 // 공통
                .requestMatchers(  "/", "/internal/**").permitAll()
                 // 로그인, 로그아웃, 회원가입, 재발급
                .requestMatchers( "/login","/logout", "/join","/reissue").permitAll()

                 // 사용자 기능
                .requestMatchers(POST, "/api/v1/users/reset-password", "/api/v1/users").permitAll()
                .requestMatchers(GET,"/api/v1/users/*","/api/v1/users/*/profile", "/api/v1/users/duplicate").permitAll()

                // SecurityContextHolder에 setAuthentication 설정 안해둬서 authenticated 실패 발생.
                .anyRequest().authenticated()
        );

        http
            .addFilterBefore(new JWTFilter(userRepository, jwtUtil), LoginFilter.class);
        //필터 추가 LoginFilter()는 인자를 받음 (AuthenticationManager() 메소드에 authenticationConfiguration 객체를 넣어야 함) 따라서 등록 필요
        http
            .addFilterAt(
                new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil,
                    refreshRepository, userRepository, profileRepository, cookieUtil),
                UsernamePasswordAuthenticationFilter.class);

        http
            .addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshRepository),
                LogoutFilter.class);

        //세션 설정
        http
            .sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
