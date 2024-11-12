package com.zerobase.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderType {
    DEPOSIT("보증금");
    private final String korean;
}
