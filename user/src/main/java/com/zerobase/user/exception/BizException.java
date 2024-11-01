package com.zerobase.user.exception;

import com.zerobase.user.dto.response.ErrorCode;
import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    private final ErrorCode errorCode;

    public BizException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}

