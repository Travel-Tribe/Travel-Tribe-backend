package com.zerobase.user.exception;

import static com.zerobase.user.dto.response.BasicErrorCode.UNAUTHORIZED_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

import com.zerobase.user.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException) {

        ResponseUtil.setJsonResponse(response, SC_UNAUTHORIZED, UNAUTHORIZED_ERROR);
    }
}
