package com.zerobase.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestPayDepositSuccess {

    private String pg_token;
    private long depositId;

}
