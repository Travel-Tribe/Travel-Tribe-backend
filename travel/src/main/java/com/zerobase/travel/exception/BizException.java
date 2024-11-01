package com.zerobase.travel.exception;

import com.zerobase.travel.exception.errorcode.ErrorCode;
import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    private final ErrorCode errorCode;

    public BizException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}

