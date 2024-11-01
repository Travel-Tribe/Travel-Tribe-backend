package com.zerobase.user.jwt;

import static com.zerobase.user.dto.response.BasicErrorCode.EXPIRED_TOKEN_ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.INVALID_REFRESH_TOKEN_CATEGORY_ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.INVALID_TOKEN_FORMAT_ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.REFRESH_TOKEN_NOT_FOUND_ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.REFRESH_TOKEN_NOT_IN_DATABASE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.user.dto.response.ResponseMessage;
import com.zerobase.user.repository.RefreshRepository;
import com.zerobase.user.util.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.GenericFilterBean;

@RequiredArgsConstructor
public class CustomLogoutFilter extends GenericFilterBean {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    @Override
    public void doFilter(
        ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws IOException, ServletException {

        //path and method verify
        String requestUri = request.getRequestURI();
        if (!requestUri.matches("^\\/logout$")) {

            filterChain.doFilter(request, response);
            return;
        }
        String requestMethod = request.getMethod();
        if (!requestMethod.equals("POST")) {

            filterChain.doFilter(request, response);
            return;
        }

        //get refresh token
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("refresh")) {

                refresh = cookie.getValue();
            }
        }

        //refresh null check
        if (refresh == null) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(
                ResponseMessage.fail(REFRESH_TOKEN_NOT_FOUND_ERROR)));
            return;
        }

        //expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(
                objectMapper.writeValueAsString(ResponseMessage.fail(INVALID_TOKEN_FORMAT_ERROR)));
            return;
        } catch (ExpiredJwtException e) {

            //response status code
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter()
                .write(objectMapper.writeValueAsString(ResponseMessage.fail(EXPIRED_TOKEN_ERROR)));
            return;
        }

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);
        if (!category.equals("refresh")) {

            //response status code
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(
                ResponseMessage.fail(INVALID_REFRESH_TOKEN_CATEGORY_ERROR)));
            return;
        }

        //DB에 저장되어 있는지 확인
        Boolean isExist = refreshRepository.existsByRefresh(refresh);
        if (!isExist) {

            //response status code
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(
                ResponseMessage.fail(REFRESH_TOKEN_NOT_IN_DATABASE)));
            return;
        }

        //로그아웃 진행
        //Refresh 토큰 DB에서 제거
        refreshRepository.deleteByRefresh(refresh);

        //Refresh 토큰 Cookie 값 0
        Cookie cookie = new Cookie("refresh", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(ResponseMessage.success()));
    }
}
