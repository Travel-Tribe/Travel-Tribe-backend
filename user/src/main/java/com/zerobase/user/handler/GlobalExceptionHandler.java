package com.zerobase.user.handler;

import static com.zerobase.user.dto.response.BasicErrorCode.ILLEGAL_ARGUMENT__ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.INTERNAL_SERVER_ERROR;
import static com.zerobase.user.dto.response.UserErrorCode.USER_NOT_FOUND_ERROR;
import static com.zerobase.user.dto.response.ValidErrorCode.DATABASE_VALID_ERROR;

import com.zerobase.user.dto.response.ErrorCode;
import com.zerobase.user.dto.response.Errors;
import com.zerobase.user.dto.response.ProfileErrorCode;
import com.zerobase.user.dto.response.ResponseMessage;
import com.zerobase.user.dto.response.UserErrorCode;
import com.zerobase.user.exception.BizException;
import com.zerobase.user.exception.TokenException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
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
            .map(fieldError -> new Errors(determineErrorCode(fieldError)))
            .collect(Collectors.toList());
        // 400 Bad Request 상태 코드와 함께 반환
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage.fail(errors));
    }

    // 필드 에러에 따라 알맞은 ErrorCode를 반환하는 메서드
    private ErrorCode determineErrorCode(FieldError fieldError) {
        String field = fieldError.getField();

        // 필드 이름 또는 조건에 따라 UserErrorCode와 ProfileErrorCode를 구분
        if (field.equals("username") || field.equals("nickname") || field.equals("phone")
            || field.equals("password") || field.equals("newPassword") || field.equals("email")) {
            return UserErrorCode.findErrorCode(fieldError);
        } else if (field.equals("mbti") || field.equals("smoking") || field.equals("gender")
            || field.equals("birth")) {
            return ProfileErrorCode.findErrorCode(fieldError);
        }
        // 예외 상황에 대한 기본 에러 코드
        return ProfileErrorCode.PROFILE_NOT_FOUND_ERROR;
    }

    // 데이터베이스 제약 조건 위반 (예: 이메일 중복)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(
        DataIntegrityViolationException ex) {
        // 409 Conflict 상태 코드와 함께 반환
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ResponseMessage.fail(DATABASE_VALID_ERROR));
    }

    // 커스텀 비즈니스 예외 처리
    @ExceptionHandler(BizException.class)
    public ResponseEntity<?> handleBizException(BizException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ResponseMessage.fail(ex.getErrorCode()));

    }

    // 토큰 예외 처리
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<?> handleBizException(TokenException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ResponseMessage.fail(ex.getErrorCode()));
    }

    // UsernameNotFoundException 처리 추가
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        // 404 Not Found 상태 코드와 함께 반환
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ResponseMessage.fail(USER_NOT_FOUND_ERROR));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        // 500 Internal Server Error 상태 코드와 함께 반환
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ResponseMessage.fail(ILLEGAL_ARGUMENT__ERROR));
    }

    // 그 외 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        // 500 Internal Server Error 상태 코드와 함께 반환
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ResponseMessage.fail(INTERNAL_SERVER_ERROR));
    }
}
