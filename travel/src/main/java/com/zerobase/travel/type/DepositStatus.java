package com.zerobase.travel.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DepositStatus {

    UNPAID("보증급미납"),
    PAID("보증금납부"),
    FORFEITED("보증금몰수"),
    RETURNED("보증급환급");

    private final String korean;

    }