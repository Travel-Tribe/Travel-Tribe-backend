package com.zerobase.controller;

import com.zerobase.model.RequestReadyPayDeposit;
import com.zerobase.model.type.ResponseDepositPayDto;
import com.zerobase.service.PayManagmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/*
 페이서비스에 결제 준비를 요청 -> client 결제정보 수신
 -> client 성공, 실패, 취소에 따라 서로다른 URL 로 연결됨.
 */
@RestController
@RequestMapping(value = "/pay")
@RequiredArgsConstructor
@Slf4j
public class PayController {

    private final PayManagmentService payManagmentService;

    // 결제시도후 payDeposit 생성하기
    @PostMapping(value = "/deposit/ready")
    public ResponseEntity<ResponseDepositPayDto> readyPayDeposit(
        @RequestBody RequestReadyPayDeposit request) {
        log.info("request ready pay deposit {}", request.toString());
        return ResponseEntity.ok(payManagmentService.readyDepositPay(
            request.getPostId(),
            request.getParticipationId(), request.getUserId(),
            request.getPaymentMethod()))
            ;

    }

    // 결제시도후 payDeposit 생성하기
    @PostMapping(value = "/deposit/success")
    public ResponseEntity<Object> CreatePayDepositSuccess() {

        return null;
    }


    // 결제시도후 payDeposit 생성하기
    @PostMapping(value = "/deposit/fail")
    public ResponseEntity<Object> CreatePayDepositFail() {

        return null;
    }

    // 결제시도후 payDeposit 생성하기
    @PostMapping(value = "/deposit/refund")
    public ResponseEntity<Object> CreatePayDepositCancel() {

        return null;
    }


    @GetMapping(value = "/deposit")
    public ResponseEntity<Object> getPayDeposit(
        @RequestParam String userId, @RequestParam Long postId) {

        return null;
    }

    @GetMapping
    public ResponseEntity<Object> getPayHistory() {

        return null;
    }


}
