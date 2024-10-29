package com.zerobase.user.util;


import static com.zerobase.user.dto.response.UserErrorCode.LOGIN_FAIL_ERROR;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.user.dto.response.LoginSuccessDTO;
import com.zerobase.user.dto.response.ResponseMessage;
import com.zerobase.user.type.Gender;
import com.zerobase.user.type.MBTI;
import com.zerobase.user.type.Smoking;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 공통 JSON 응답을 설정하는 메서드
     */
    public static void setJsonResponse(HttpServletResponse response, int statusCode, LoginSuccessDTO loginSuccessDTO) throws IOException {
        response.setStatus(statusCode);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        String jsonResponse = objectMapper.writeValueAsString(ResponseMessage.success(loginSuccessDTO));

        PrintWriter writer = response.getWriter();
        writer.write(jsonResponse);
        writer.flush();
    }

    public static void setJsonResponse(HttpServletResponse response,  int statusCode) throws IOException {
        response.setStatus(statusCode);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        String jsonResponse = objectMapper.writeValueAsString(ResponseMessage.fail(LOGIN_FAIL_ERROR));

        PrintWriter writer = response.getWriter();
        writer.write(jsonResponse);
        writer.flush();
    }
}
