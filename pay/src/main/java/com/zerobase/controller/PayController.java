package com.zerobase.controller;

import com.zerobase.model.RequestPayDepositSuccess;
import com.zerobase.model.RequestReadyPayDeposit;
import com.zerobase.model.ResponseDepositPayDto;
import com.zerobase.model.ResponsePaymentDto;
import com.zerobase.model.PaymentDto;
import com.zerobase.service.PayManagementService;
import com.zerobase.service.PaymentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
 페이서비스에 결제 준비를 요청 -> client 결제정보 수신
 -> client 성공, 실패, 취소에 따라 서로다른 URL 로 연결됨.
 */
@RestController
@RequestMapping(value = "/api/v1/pay")
@RequiredArgsConstructor
@Slf4j
public class PayController {

    private final PayManagementService payManagmentService;
    private final PaymentService paymentService;


    @GetMapping(value = "/list")
    public ResponseEntity<List<ResponsePaymentDto>> getPayHistory(
        @RequestHeader("X-User-Id") String userId) {

        List<PaymentDto> paymentDtos = paymentService.getPayments(userId);
        return ResponseEntity.ok(
            paymentDtos.stream().map(ResponsePaymentDto::fromDto).toList());
    }

    // 결제시도후 payDeposit 생성하기
    @PostMapping(value = "/deposit/ready")
    public ResponseEntity<ResponseDepositPayDto> readyPayDeposit(
        @RequestBody RequestReadyPayDeposit request, @RequestHeader("X-User-Id") String userId) {
        log.info("request ready pay deposit ");
        return ResponseEntity.ok(
            payManagmentService.createDepositOrderAndInitiatePay(
                request.getPostId(),
                request.getParticipationId(), userId,
                request.getPgMethod()))
            ;
    }

    // client 결제성공시 확정처리를 위해서 신호받기
    @PutMapping(value = "/deposit/success")
    public ResponseEntity<Object> PayDepositSuccess(
        @RequestBody RequestPayDepositSuccess request,
        @RequestHeader("X-User-Id") String userId) {
        log.info(" pay deposit success sign from client");
        payManagmentService.clientSuccessDepositPay(
            request.getParticipationId(),userId, request.getPg_token());

        return ResponseEntity.ok().build();
    }


    // client 결제실패시 url 신호받기
    // todo: frontend와 회의후 결과에 따라 코드 재작성하기
    @PutMapping(value = "/deposit/fail")
    public ResponseEntity<Object> PayDepositFail(
        @RequestBody RequestPayDepositFail request,
        @RequestHeader("X-User-Id") String userId
    ) {
        log.info(" pay deposit fail sign from client");
        payManagmentService.failedDepositPay(userId);

        return ResponseEntity.ok().build();
    }


}
