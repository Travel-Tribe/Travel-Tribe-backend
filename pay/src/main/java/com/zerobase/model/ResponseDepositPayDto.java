package com.zerobase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDepositPayDto {



    private Long postId;
    private Long participationId;
    private Long depositId;
    private Long amount;
    private String userId;
    private String nextRedirectPcUrl;





}
