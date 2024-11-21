package com.zerobase.exception;

import com.zerobase.exception.errorCode.PaymentErrorCode;
import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    private final PaymentErrorCode errorCode;

    public BizException(PaymentErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
