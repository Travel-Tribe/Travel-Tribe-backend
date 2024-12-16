package com.zerobase.model;

import com.zerobase.model.type.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestPayDepositResult {

    private PaymentStatus result;
    private String pg_token="";

}
