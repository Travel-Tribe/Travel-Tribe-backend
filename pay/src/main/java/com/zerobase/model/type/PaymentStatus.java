package com.zerobase.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PaymentStatus {
    PAY_IN_PROGRESS("결제중"),
    PAY_COMPLETED("결제완료"),
    PAY_FAILED("결제실패"),
    PAY_REFUNDED("결제환불");

    private final String korean;

    }



