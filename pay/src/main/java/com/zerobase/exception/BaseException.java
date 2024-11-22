package com.zerobase.exception;

import com.zerobase.exception.errorCode.PaymentErrorCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final PaymentErrorCode errorCode;

    public BaseException(PaymentErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}

