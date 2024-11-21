package com.zerobase.travel.handler;


import com.zerobase.travel.common.response.Errors;
import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.TokenException;
import com.zerobase.travel.exception.errorcode.BasicErrorCode;
import com.zerobase.travel.exception.errorcode.ValidErrorCode;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 전역 예외 처리 핸들러 추가
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 유효성 검증 실패 시 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {

        log.error(ex.getMessage());

        List<Errors> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> new Errors(ValidErrorCode.findErrorCode(fieldError)))
            .collect(Collectors.toList());
        // 400 Bad Request 상태 코드와 함께 반환
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ResponseMessage.fail(errors));
    }

    // 커스텀 비즈니스 예외 처리
    @ExceptionHandler(BizException.class)
    public ResponseEntity<?> handleBizException(BizException ex) {

        log.error(ex.getMessage());

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ResponseMessage.fail(ex.getErrorCode()));

    }



    // 토큰 예외 처리
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<?> handleBizException(TokenException ex) {
        log.error(ex.getMessage());

        return ResponseEntity
            .status(ex.getHttpStatus())
            .body(ResponseMessage.fail(ex.getErrorCode()));
    }

    // 그 외 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {

        log.error(ex.getMessage(), ex);

        // 500 Internal Server Error 상태 코드와 함께 반환
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ResponseMessage.fail(BasicErrorCode.INTERNAL_SERVER_ERROR));
    }
}
