package com.zerobase.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PayMethod {
    MONEY("현금"),
    CARD("카드");

    private final String korean;

}
