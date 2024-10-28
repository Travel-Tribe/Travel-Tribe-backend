package com.zerobase.user.util;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {

    /**
     * 공통 JSON 응답을 설정하는 메서드
     */
    public static void setJsonResponse(HttpServletResponse response, boolean result, String resultCode, int statusCode, String message) throws IOException {
        response.setStatus(statusCode);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        String jsonResponse = "{ \"header\": {"
            + "\"result\": " + result + ","
            + "\"resultCode\": \"" + resultCode + "\","
            + "\"message\": \"" + message + "\","
            + "\"status\": " + statusCode
            + "} }";

        PrintWriter writer = response.getWriter();
        writer.write(jsonResponse);
        writer.flush();
    }
}
