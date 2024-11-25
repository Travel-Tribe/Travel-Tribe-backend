package com.zerobase.travel.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@RequiredArgsConstructor
public class PayApi {

    private final com.zerobase.travel.api.PayClient payClient;

    public ResponseEntity<Object> payDepositRefund( long participationId, String userId) {

        payClient.payDepositRefund(
            RequestpayDepositRefund
                .builder()
                    .participationId(participationId)
                        .userId(userId).build());

        return ResponseEntity.ok().build();
    }


}