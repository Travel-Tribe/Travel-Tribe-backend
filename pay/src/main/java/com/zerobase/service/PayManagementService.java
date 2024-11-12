package com.zerobase.service;

import static com.zerobase.config.Constants.DEPOSIT_AMOUNT;
import static com.zerobase.config.Constants.TAX_FREE_AMOUNT;

import com.zerobase.api.KakaopayApi;
import com.zerobase.api.TravelApi;
import com.zerobase.entity.DepositEntity;
import com.zerobase.entity.PaymentEntity;
import com.zerobase.model.DepositDto;
import com.zerobase.model.ResponseApi;
import com.zerobase.model.ResponseDepositPayDto;
import com.zerobase.model.type.PGMethod;
import com.zerobase.model.PaymentDto.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PayManagementService {

    private final DepositService depositService;
    private final KakaopayApi kakaopayApi;
    private final PaymentService paymentService;
    private final TravelApi travelApi;

    /*
    1. travel 모듈에서 여행참가(participationId) 데이터가 생성될 때 pay module로 참가 데이터를 발송함.
    2. 참가 데이터를 받은 결제모듈은 상품주문(depositId) 데이터를 생성하고 PG사 API를 통해 결제진행 요청을 받은 후
    거래번호(tid)와 결제인증 url을 응답받음.
    3. 결제 Id역시 생성함
    4. 이중 url은 client로 발송하며 tid는 내부저장함
     */


    public ResponseDepositPayDto createDepositOrderAndInitiatePay(
        long postId, long participationId, String userId, PGMethod pgMethod) {
        log.info("readyDepositPay");

        // deposit 생성
        DepositEntity depositEntity = depositService.createDepositOrder(postId,
            participationId, userId);

        // pg 사와 통신을 통해 tid 추출
        ResponseApi.PayReadyApiDto payReadyApiDto = kakaopayApi.sendPayReadySign(
            depositEntity.getDepositId(), userId);

        // tid를 기반으로 결제데이터 생성 & 카카오페이 결제 이력 저장
        PaymentDto payment = paymentService.createPaymentAndSave(
            depositEntity.getDepositId(), userId, payReadyApiDto.getTid(),
            pgMethod);

        depositService.save(depositEntity);

        return ResponseDepositPayDto.builder()
            .depositId(depositEntity.getDepositId())
            .postId(depositEntity.getPostId())
            .participationId(depositEntity.getParticipationId())
            .userId(depositEntity.getUserId())
            .amount(payment.getAmount())
            .next_redirect_pc_url(payReadyApiDto.getNext_redirect_pc_url())
            .build();
    }


    /*
    1. 사용자는 pg사 로부터 결제 인증을 성공하면 성공 url로 연결되어 queryparam으로 pg token을 전송함
    2. 이전 단계에서 보낸 tid와 이번에 받은 pgtoken을 사용하여 카카오 서버에 확정 신호를 보냄
    3. 페이 히스토리 entity에 이력을 저장하고 deposit과 payment entity의 상태를 변경함

     */

    public void clientSuccessDepositPay(long participationId,
        String userId, String pgToken) {
        log.info("success customer DepositPay");

        PaymentEntity paymentEntity = paymentService.ChangeStatusToCompleteByUserId(
            userId);

        kakaopayApi.sendPayConfirmSign(
            paymentEntity.getPaykey(), paymentEntity.getReferentialOrderId(),
            userId, pgToken);

        travelApi.confirmParticipation(participationId,userId);

        paymentService.savePayments(paymentEntity);
    }

    /*
    1. 사용자는 pg사 로부터 결제 인증을 실패하면 실패 url로 연결되어
    2. 페이 히스토리 entity에 이력을 저장하고 deposit과 payment entity의 상태를 변경함
*/

    public void failedDepositPay(String userId) {
        log.info("fail customer DepositPay");
        PaymentEntity paymentEntity = paymentService.ChangeStatusToFailByUserId(userId);
        paymentService.savePayments(paymentEntity);
    }



    /*
    tid 정보를 통해서 server 측에서 pg사에 API를 통해 취소요청이 가능함.
    client 측에서는 participation을 기준으로 취소요청을 할 것으로 생각됨.
    */

    public void refundDepositPay(
        Long participationId) {
        log.info("refund customer DepositPay");

        DepositDto depositDto = depositService.findByParticipationId(
            participationId);

        PaymentEntity paymentEntity = paymentService.
            ChangeStatusToRefundedByOrderId(depositDto.getDepositId());

        kakaopayApi.sendPayRefundSign(
                paymentEntity.getPaykey(), DEPOSIT_AMOUNT, TAX_FREE_AMOUNT);

        paymentService.savePayments(paymentEntity);
    }
}



