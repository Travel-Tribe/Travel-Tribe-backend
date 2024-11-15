package com.zerobase.controller;

import com.zerobase.model.RequestpayDepositRefund;
import com.zerobase.service.PayManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
 페이서비스에 결제 준비를 요청 -> client 결제정보 수신
 -> client 성공, 실패, 취소에 따라 서로다른 URL 로 연결됨.
 */
@RestController
@RequestMapping(value = "/internal/api/v1/pay")
@RequiredArgsConstructor
@Slf4j
public class InternalPayController {

    private final PayManagementService payManagementService;


    // client 결제환불시 url 신호받기
    @PutMapping(value = "/deposit/refund")
    public ResponseEntity<Object> payDepositRefund(
        RequestpayDepositRefund request) {
        log.info(" pay deposit refund sign from client");
         payManagementService.refundDepositPay(request.getDepositId(), request.getUserId());
        return ResponseEntity.ok().build();
    }


}
