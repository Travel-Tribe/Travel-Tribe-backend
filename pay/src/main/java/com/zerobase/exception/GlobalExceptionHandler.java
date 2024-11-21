package com.zerobase.exception;

import com.zerobase.exception.errorCode.PaymentErrorCode;
import com.zerobase.model.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions() {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ResponseMessage.fail(PaymentErrorCode.REQUEST_VALIDATION_ERROR));
    }

    @ExceptionHandler(BizException.class)
    public ResponseEntity<?> handleBizException(BizException ex) {

        log.info(ex.getMessage());

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ResponseMessage.fail(ex.getErrorCode()));

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ResponseMessage.fail(PaymentErrorCode.INTERNAL_SERVER_ERROR));
    }





}
