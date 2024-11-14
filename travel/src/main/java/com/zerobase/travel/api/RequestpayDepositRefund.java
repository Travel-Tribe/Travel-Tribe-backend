package com.zerobase.travel.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequestpayDepositRefund {
    private long participationId;
    private String userId;


}