package com.zerobase.user.handler;

import static com.zerobase.user.dto.response.BasicErrorCode.ILLEGAL_ARGUMENT__ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.INTERNAL_SERVER_ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.REFRESH_TOKEN_ERROR;
import static com.zerobase.user.dto.response.UserErrorCode.USER_NOT_FOUND_ERROR;
import static com.zerobase.user.dto.response.ValidErrorCode.DATABASE_VALID_ERROR;
import static com.zerobase.user.dto.response.ValidErrorCode.VALID_ERROR;

import com.zerobase.user.dto.response.ResponseMessage;
import com.zerobase.user.dto.response.UserErrorCode;
import com.zerobase.user.dto.response.ValidErrorCode;
import com.zerobase.user.dto.response.Errors;
import com.zerobase.user.exception.BizException;
import com.zerobase.user.exception.TokenException;
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
        List<Errors> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> new Errors(UserErrorCode.findErrorCode(fieldError)))
            .collect(Collectors.toList());
        // 400 Bad Request 상태 코드와 함께 반환
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage.fail(errors));
    }

    // 데이터베이스 제약 조건 위반 (예: 이메일 중복)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // 409 Conflict 상태 코드와 함께 반환
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseMessage.fail(DATABASE_VALID_ERROR));
    }

    // 커스텀 비즈니스 예외 처리
    @ExceptionHandler(BizException.class)
    public ResponseEntity<?> handleBizException(BizException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage.fail(DATABASE_VALID_ERROR));

    }

    // 토큰 예외 처리
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<?> handleBizException(TokenException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseMessage.fail(ex.getErrorCode()));
    }

    // UsernameNotFoundException 처리 추가
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        // 404 Not Found 상태 코드와 함께 반환
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseMessage.fail(USER_NOT_FOUND_ERROR));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        // 500 Internal Server Error 상태 코드와 함께 반환
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage.fail(ILLEGAL_ARGUMENT__ERROR));
    }

    // 그 외 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        // 500 Internal Server Error 상태 코드와 함께 반환
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.fail(INTERNAL_SERVER_ERROR));
    }
}
