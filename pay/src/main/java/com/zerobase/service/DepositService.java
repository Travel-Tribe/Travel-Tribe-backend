package com.zerobase.service;

import com.zerobase.api.ParticipationApi;
import com.zerobase.entity.DepositEntity;
import com.zerobase.exception.BizException;
import com.zerobase.exception.errorCode.PaymentErrorCode;
import com.zerobase.model.DepositDto;
import com.zerobase.model.type.PaymentStatus;
import com.zerobase.repository.DepositRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class DepositService {

    private final DepositRepository depositRepository;
    private final ParticipationApi participationAPi;


    // depositId를 생성하기 위해서
    public DepositEntity createAndSaveDepositOrder(Long postId,
        Long participationId,
        String userId) {
        log.info("DepositRepository initial save start");

        DepositEntity entity = DepositEntity.builder()
            .postId(postId)
            .paymentStatus(PaymentStatus.PAY_IN_PROGRESS)
            .participationId(participationId)
            .userId(userId)
            .build();

        return depositRepository.save(entity);
    }


    public DepositDto findByParticipationIdAndChangStatus(Long participationId) {
        return DepositDto.fromEntity(depositRepository
            .findByParticipationIdAndPaymentStatus(participationId,
                PaymentStatus.PAY_COMPLETED).orElseThrow(
                () -> new BizException(PaymentErrorCode.DEPOSIT_NOT_EXSITING)));
    }

    public void save(DepositEntity depositEntity) {
        depositRepository.save(depositEntity);
    }

    public void validateDepositCreateRequest(long postId, long participationId,
        String userId) {
        if (Boolean.FALSE.equals(participationAPi.validateParticipationInfo
                (postId, participationId, userId).getBody())) {
            throw new BizException(PaymentErrorCode.INVALID_PARTICIPATION_INFORMATION);
        }

        if(depositRepository.existsByParticipationIdAndPaymentStatus(participationId,PaymentStatus.PAY_COMPLETED))
            throw new BizException(PaymentErrorCode.DEPOSIT_ALREADY_PAID);


    }

    public DepositEntity getPaymentInProgressAndchangeStatusByOrderId(
        long depositId,
        PaymentStatus paymentStatus) {
        DepositEntity depositEntity = depositRepository.findById(depositId)
            .orElseThrow(() -> new BizException(
                PaymentErrorCode.DEPOSIT_NOT_EXSITING));



        depositEntity.setPaymentStatus(paymentStatus);

        return depositEntity;
    }

    public DepositEntity getPaymentCompletedAndChangeStatusByOrderId(
        long depositId,
        PaymentStatus paymentStatus) {
        DepositEntity depositEntity = depositRepository.findById(depositId)
            .orElseThrow(() -> new BizException(
                PaymentErrorCode.DEPOSIT_NOT_EXSITING));

        depositEntity.setPaymentStatus(paymentStatus);

        return depositEntity;
    }

    public DepositEntity SetToRefundDepositPay(long participationId, String userId) {
        DepositEntity depositEntity = depositRepository.findByParticipationIdAndPaymentStatus(
            participationId,PaymentStatus.PAY_COMPLETED).orElseThrow(() -> new BizException(PaymentErrorCode.DEPOSIT_NOT_EXSITING));

        this.validateDepositInfo(depositEntity,participationId,userId,PaymentStatus.PAY_COMPLETED);

        depositEntity.setPaymentStatus(PaymentStatus.PAY_REFUNDED);

        return depositEntity;

    }

    private void validateDepositInfo(DepositEntity depositEntity, long participationId, String userId, PaymentStatus paymentStatus) {

        if(depositEntity.getParticipationId()!=participationId) throw new BizException(PaymentErrorCode.INVALID_PARTICIPATION_INFORMATION);

        if(!Objects.equals(depositEntity.getUserId(), userId)) throw new BizException(PaymentErrorCode.INVALID_PARTICIPATION_INFORMATION);

        if(depositEntity.getPaymentStatus()!=paymentStatus) throw new BizException(PaymentErrorCode.INVALID_DEPOSIT_INFORMATION);

    }
}
