package com.zerobase.service;

import static com.zerobase.config.Constants.DEPOSIT_AMOUNT;
import static com.zerobase.config.Constants.TAX_FREE_AMOUNT;

import com.zerobase.model.DepositDto;
import com.zerobase.model.ResponseApi;
import com.zerobase.model.ResponseDepositPayDto;
import com.zerobase.model.exception.CustomException;
import com.zerobase.model.type.PGMethod;
import com.zerobase.model.type.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PayManagementService {

    private final DepositService depositService;
    private final KakaopayApiService kakaopayApiService;
    private final PaymentService paymentService;

    /*
    1. travel 모듈에서 여행참가(participationId) 데이터가 생성될 때 pay module로 참가 데이터를 발송함.
    2. 참가 데이터를 받은 결제모듈은 상품주문(depositId) 데이터를 생성하고 PG사 API를 통해 결제진행 요청을 받은 후
    거래번호(tid)와 결제인증 url을 응답받음.
    3. 결제 Id역시 생성함
    4. 이중 url은 client로 발송하며 tid는 내부저장함
     */
    @Transactional
    public ResponseDepositPayDto createDepositOrderAndInitiatePay(
        Long postId, Long participationId, String userId, PGMethod pgMethod) {
        log.info("readyDepositPay");

        // deposit 생성
        DepositDto depositDto = depositService.createDepositOrder(postId,
            participationId, userId);

        // pg 사와 통신을 통해 tid 추출
        ResponseApi.PayReadyApiDto payReadyApiDto = kakaopayApiService.sendPayReadySign(
            depositDto.getDepositId(), userId);

        // tid를 기반으로 결제데이터 생성 & 카카오페이 결제 이력 저장
        PaymentDto payment = paymentService.createPayment(
            depositDto.getDepositId(), userId, payReadyApiDto.getTid(),
            pgMethod);

        return ResponseDepositPayDto.builder()
            .depositId(depositDto.getDepositId())
            .postId(depositDto.getPostId())
            .participationId(depositDto.getParticipationId())
            .userId(depositDto.getUserId())
            .amount(payment.getAmount())
            .next_redirect_pc_url(payReadyApiDto.getNext_redirect_pc_url())
            .build();
    }


    /*
    1. 사용자는 pg사 로부터 결제 인증을 성공하면 성공 url로 연결되어 queryparam으로 pg token을 전송함
    2. 이전 단계에서 보낸 tid와 이번에 받은 pgtoken을 사용하여 카카오 서버에 확정 신호를 보냄
    3. 페이 히스토리 entity에 이력을 저장하고 deposit과 payment entity의 상태를 변경함

     */

    @Transactional
    public void successDepositPay(
        String userId, String pgToken) {
        log.info("success customer DepositPay");

        PaymentDto paymentDto = paymentService.ChangeStatusToCompleteByUserId(
            userId);

        try {
            kakaopayApiService.sendPayConfirmSign(
                paymentDto.getPayKey(), paymentDto.getReferentialOrderId(),
                userId, pgToken);
        } catch (Exception e) {
            throw new CustomException();
        }


    }

    /*
    1. 사용자는 pg사 로부터 결제 인증을 성공하면 성공 url로 연결되어 서버에 tid와 pg token을 전송함
    2. 페이 히스토리 entity에 이력을 저장하고 deposit과 payment entity의 상태를 변경함
*/

    public void failedDepositPay(String tid) {
        log.info("fail customer DepositPay");

        paymentService.ChangeStatusToFailByUserId(tid);

    }



    /*
    tid 정보를 통해서 server 측에서 pg사에 API를 통해 취소요청이 가능함.
    client 측에서는 participation을 기준으로 취소요청을 할 것으로 생각됨.
    */


    @Transactional
    public void refundDepositPay(
        Long participationId) {
        log.info("refund customer DepositPay");

        DepositDto depositDto = depositService.findByParticipationId(
            participationId);

        PaymentDto paymentDto = paymentService.
            ChangeStatusToRefundedByOrderId(depositDto.getDepositId());

        try {
            kakaopayApiService.sendPayRefundSign(
                paymentDto.getPayKey(), DEPOSIT_AMOUNT, TAX_FREE_AMOUNT);
        } catch (Exception e) {
            throw new CustomException();
        }


    }
}



