package com.zerobase.travel.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@FeignClient(name = "pay-service", url = "${pay-service.url}") // application.yml에 정의된 URL 사용
public interface PayClient {

    @PutMapping("/internal/api/v1/pay/deposit/refund")
    ResponseEntity<Object> payDepositRefund( RequestpayDepositRefund request);

}