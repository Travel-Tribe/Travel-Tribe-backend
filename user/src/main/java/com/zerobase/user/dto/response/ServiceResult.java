package com.zerobase.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResult {

    private boolean result;
    private String message;
    private HttpStatus status;  // HTTP 상태 코드 추가

    public static ServiceResult fail(HttpStatus httpStatus, String message) {
        return ServiceResult.builder()
            .result(false)
            .status(httpStatus)
            .message(message)
            .build();
    }

    public static ServiceResult success(HttpStatus httpStatus, String message) {
        return ServiceResult.builder()
            .result(true)
            .status(httpStatus)
            .message(message)
            .build();
    }

    public boolean isFail() {
        return !result;
    }

}
