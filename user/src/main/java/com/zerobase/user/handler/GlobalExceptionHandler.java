package com.zerobase.user.handler;

import com.zerobase.user.exception.BizException;
import com.zerobase.user.dto.response.ResponseError;
import com.zerobase.user.dto.response.ResponseResult;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 전역 예외 처리 핸들러 추가
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 유효성 검증 실패 시 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ResponseError> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(ResponseError::of)
            .collect(Collectors.toList());
        // 400 Bad Request 상태 코드와 함께 반환
        return ResponseResult.fail(HttpStatus.BAD_REQUEST, "유효성 검사 실패", errors);
    }

    // 데이터베이스 제약 조건 위반 (예: 이메일 중복)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // 409 Conflict 상태 코드와 함께 반환
        return ResponseResult.fail(HttpStatus.CONFLICT, ex.getMessage(), null);
    }

    // 커스텀 비즈니스 예외 처리 (동적 상태 코드 및 메시지 처리)
    @ExceptionHandler(BizException.class)
    public ResponseEntity<?> handleBizException(BizException ex) {
        // BizException에서 상태 코드와 메시지를 동적으로 가져와서 반환
        return ResponseResult.fail(ex.getStatus(), ex.getMessage(), null);
    }

    // UsernameNotFoundException 처리 추가
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        // 404 Not Found 상태 코드와 함께 반환
        return ResponseResult.fail(HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }

    // 그 외 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        // 500 Internal Server Error 상태 코드와 함께 반환
        return ResponseResult.fail(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다.", null);
    }
}
