package com.zerobase.controller.v2;

import com.zerobase.model.PaymentDto;
import com.zerobase.model.RequestPayDepositFail;
import com.zerobase.model.RequestPayDepositResult;
import com.zerobase.model.RequestPayDepositSuccess;
import com.zerobase.model.RequestReadyPayDeposit;
import com.zerobase.model.ResponseDepositPayDto;
import com.zerobase.model.ResponseMessage;
import com.zerobase.model.ResponsePaymentDto;
import com.zerobase.service.PayManagementService;
import com.zerobase.service.PaymentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "/api/v2/pay")
@RequiredArgsConstructor
@Slf4j
public class PayController {

    private final PayManagementService payManagmentService;
    private final PaymentService paymentService;


    @GetMapping(value = "/payment")
    public ResponseEntity<ResponseMessage<List<ResponsePaymentDto>>> getPayHistory(
        @RequestHeader("X-User-Id") String userId) {

        List<PaymentDto> paymentDtos = paymentService.getPaymentsByUserId(userId);
        return ResponseEntity.ok(ResponseMessage.success(
            paymentDtos.stream().map(ResponsePaymentDto::fromDto).toList()));
    }

    // 결제시도후 payDeposit 생성하기
    @PostMapping(value = "/deposit")
    public ResponseEntity<ResponseMessage<ResponseDepositPayDto>> readyPayDeposit(
        @RequestBody RequestReadyPayDeposit request,
        @RequestHeader("X-User-Id") String userId) {
        log.info("request ready pay deposit ");
        return ResponseEntity.ok(ResponseMessage.success(
            payManagmentService.createDepositOrderAndInitiatePay(
                request.getPostId(),
                request.getParticipationId(), userId,
                request.getPgMethod())))
            ;
    }

    // client 결제성공시 확정처리를 위해서 신호받기
    @PutMapping(value = "/deposit/{deposit_id}")
    public ResponseEntity<ResponseMessage<Object>> PayDepositResult(
        @PathVariable long deposit_id,
        @RequestBody RequestPayDepositResult request,
        @RequestHeader("X-User-Id") String userId) {
        log.info(" pay deposit success sign from client");
        payManagmentService.handleClientDepositPayResult(request.getResult(),
            deposit_id,userId, request.getPg_token());

        return ResponseEntity.ok(ResponseMessage.success());
    }



}
