package com.zerobase.travel.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@RequiredArgsConstructor
public class PayApi {

    private final PayClient payClient;

    public ResponseEntity<Object> payDepositRefund(@PathVariable long participationId) {

        payClient.payDepositRefund(participationId);

        return ResponseEntity.ok().build();
    }


}
