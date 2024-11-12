package com.zerobase.model.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum KaKaoPayStatus {
    SUCCESS("결제성공"),
    FAILED("결제실패"),
    REFUND("결제취소");

    private final String korean;
}
