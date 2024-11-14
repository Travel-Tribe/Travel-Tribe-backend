package com.zerobase.service;



import com.zerobase.api.KakaopayApi;
import com.zerobase.api.TravelApi;
import com.zerobase.config.Constants;
import com.zerobase.entity.DepositEntity;
import com.zerobase.entity.PaymentEntity;
import com.zerobase.model.PaymentDto;
import com.zerobase.model.ResponseApi;
import com.zerobase.model.ResponseDepositPayDto;
import com.zerobase.model.type.PGMethod;
import com.zerobase.model.type.PaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PayManagementService {

    private final DepositService depositService;
    private final KakaopayApi kakaopayApi;
    private final PaymentService paymentService;
    private final TravelApi travelApi;
    private final Constants constants;

    /*
    1. travel 모듈에서 여행참가(participationId) 데이터가 생성될 때 pay module로 참가 데이터를 발송함.
    2. 참가 데이터를 받은 결제모듈은 상품주문(depositId) 데이터와 결제데이터(paymentId)를 생성하여
    PG사 API 호출(결재준비)로 거래번호(tid)와 결제인증 url을 응답받음.
    3. client는 카카오서버를 통해서 결제를 진행하고 결제 결과에 따라 백엔드 서버에 결제성공, 결제실패 api를 호출한다
    4. 결제성공과 실패에 따라서 payment와 deposit의 상태를 변경시키고, travel모듈에 결과를 통보하여 participationId의 상태도 변경시킨다.
    5. 결제실패시에는 첫단계부터 재시도하여 participationId, depositId, paymentId를 새로 만든다.
     */


    public ResponseDepositPayDto createDepositOrderAndInitiatePay(
        long postId, long participationId, String userId, PGMethod pgMethod) {
        log.info("readyDepositPay");

        depositService.validateDepositCreateRequest(postId,participationId,userId);

        // deposit 생성과 저장 ; 저장해야 depositId생성가능
        DepositEntity depositEntity = depositService.createAndSaveDepositOrder(postId,
            participationId, userId);

        // pg 사와 통신을 통해 tid 추출
        ResponseApi.PayReadyApiDto payReadyApiDto = kakaopayApi.sendPayReadySign(
            depositEntity.getDepositId(), userId);

        // tid를 기반으로 결제데이터 생성 & 카카오페이 결제 이력 저장
        PaymentDto payment = paymentService.createPaymentAndSave(
            depositEntity.getDepositId(), userId, payReadyApiDto.getTid(),
            pgMethod);


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

    @Transactional
    public void clientSuccessDepositPay(long depositId,
         String userId, String pgToken) {
        log.info("success customer DepositPay");


        // payment와 deposit 상태변경
        PaymentEntity paymentEntity = paymentService.getPaymentsInProgressAndChangeStatusByOrderId(
            depositId, PaymentStatus.PAY_COMPLETED);

        DepositEntity depositEntity = depositService.getPaymentInProgressAndchangeStatusByOrderId(
            depositId, PaymentStatus.PAY_COMPLETED);

        // api측에 결제확정 통신
        kakaopayApi.sendPayConfirmSign(paymentEntity.getPaykey(),
            paymentEntity.getReferentialOrderId(), userId, pgToken);

        // travel 모듈쪽에 결제확정 통신
        travelApi.confirmParticipation(depositEntity.getParticipationId(),userId);

        // 두가지가 성공하고나면 payment, deposit entity저장
        paymentService.save(paymentEntity);
        depositService.save(depositEntity);


    }

    /*
    1. 사용자는 pg사 로부터 결제 인증을 실패하면 실패 url로 연결되어 해당 메소드를 실행함
    2. 페이 히스토리 entity에 이력을 저장하고 deposit과 payment entity의 상태를 변경함
    */

    @Transactional
    public void clientFailedDepositPay(String userId, long depositId) {
        log.info("fail customer DepositPay");

        DepositEntity depositEntity = depositService.getPaymentInProgressAndchangeStatusByOrderId(
            depositId, PaymentStatus.PAY_FAILED);

        PaymentEntity paymentEntity = paymentService.getPaymentsInProgressAndChangeStatusByOrderId(
            depositId, PaymentStatus.PAY_FAILED);

        paymentService.changeStatus(paymentEntity, PaymentStatus.PAY_FAILED);


        // travel 모듈쪽에 결제확정 통신
        travelApi.confirmParticipation(depositEntity.getParticipationId(),userId);


        // 두가지가 성공하고나면 payment, deposit entity저장
        depositService.save(depositEntity);
        paymentService.save(paymentEntity);
    }



    /*
    tid 정보를 통해서 server 측에서 pg사에 API를 통해 취소요청이 가능함.
    client 측에서는 participation을 기준으로 취소요청을 할 것으로 생각됨.
    */

    public void refundDepositPay(
        long depositId, String userId) {
        log.info("refund customer DepositPay");

        DepositEntity depositEntity = depositService.getPaymentInProgressAndchangeStatusByOrderId(
            depositId, PaymentStatus.PAY_REFUNDED);

        PaymentEntity paymentEntity = paymentService.getPaymentsInProgressAndChangeStatusByOrderId(
            depositId, PaymentStatus.PAY_REFUNDED);



        kakaopayApi.sendPayRefundSign(
                paymentEntity.getPaykey(), constants.DEPOSIT_AMOUNT, constants.TAX_FREE_AMOUNT);

        paymentService.save(paymentEntity);
        depositService.save(depositEntity);
    }
}



