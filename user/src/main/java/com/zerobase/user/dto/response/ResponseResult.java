package com.zerobase.user.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseResult {

    // 상태 코드 없이 실패
    public static ResponseEntity<?> fail(String message) {
        return fail(HttpStatus.BAD_REQUEST, message, null);
    }

    // 상태 코드 없이 실패 (데이터 포함)
    public static ResponseEntity<?> fail(String message, Object data) {
        return fail(HttpStatus.BAD_REQUEST, message, data);
    }

    // 상태 코드 포함 실패
    public static ResponseEntity<?> fail(HttpStatus status, String message, Object data) {
        return ResponseEntity.status(status).body(ResponseMessage.fail(status, message, data));
    }

    // 상태 코드 없이 성공
    public static ResponseEntity<?> success(String message) {
        return success(HttpStatus.OK, message, null);
    }

    // 상태 코드 없이 성공 (데이터 포함)
    public static ResponseEntity<?> success(String message, Object data) {
        return success(HttpStatus.OK, message, data);
    }

    // 상태 코드 포함 성공
    public static ResponseEntity<?> success(HttpStatus status, String message, Object data) {
        return ResponseEntity.status(status).body(ResponseMessage.success(status, message, data));
    }

    // ServiceResult 기반 응답 처리
    public static ResponseEntity<?> result(ServiceResult result) {
        if (result.isFail()) {
            return fail(HttpStatus.BAD_REQUEST, result.getMessage(), null);
        }
        return success(HttpStatus.OK, result.getMessage(), null);
    }
}
