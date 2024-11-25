package com.zerobase.exception;

import com.zerobase.exception.errorCode.PaymentErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Errors {

    private String errorCode;
    private String errorMessage;

    public Errors(PaymentErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }
}
