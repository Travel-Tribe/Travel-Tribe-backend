package com.zerobase.exception.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentErrorCode {
    DEPOSIT_NOT_EXSITING("ERROR-PAYMENT-00001", "존재하지 않는 DEPOSIT ID입니다."),
    PAYMENT_NOT_EXSITING("ERROR-PAYMENT-00002", "존재하지 않는 PAYMENT ID입니다."),
    DESPOSIT_ALREADY_PAID("ERROR-PAYMENT-00003", "해당 Deposit은 이미 결제완료되었습니다"),
    REQUEST_VALIDATION_ERROR("ERROR-PAYMENT-00004", "Validation Error"),
    INVALID_PARTICIPATION_INFORMATION("ERROR-PAYMENT-00005", "잘못된 참여 정보입니다."),
    INTERNAL_SERVER_ERROR("ERROR-PAYMENT-00006", "서버 내 에러입니다.");

    private final String errorCode;
    private final String errorMessage;


}
