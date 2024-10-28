package com.zerobase.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BizException extends RuntimeException {
    private final HttpStatus status;

    public BizException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}

