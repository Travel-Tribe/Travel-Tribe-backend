package com.zerobase.service;

import com.zerobase.api.ParticipationApi;
import com.zerobase.entity.DepositEntity;
import com.zerobase.model.DepositDto;
import com.zerobase.model.exception.CustomException;
import com.zerobase.model.exception.ErrorCode;
import com.zerobase.model.type.PaymentStatus;
import com.zerobase.repository.DepositRepository;
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


    public DepositDto findByParticipationId(Long participationId) {
        return DepositDto.fromEntity(depositRepository
            .findByParticipationId(participationId).orElseThrow(
                () -> new CustomException(ErrorCode.DEPOSIT_NOT_EXSITING)));
    }

    public void save(DepositEntity depositEntity) {
        depositRepository.save(depositEntity);
    }

    public void validateDepositCreateRequest(long postId, long participationId,
        String userId) {
        if (Boolean.FALSE.equals(participationAPi.validateParticipationInfo
                (postId, participationId, userId).getBody())) {
            throw new CustomException(ErrorCode.INVALID_CLIENT_REQUEST);
        }

        if(depositRepository.existsByParticipationIdAndStatus(participationId,PaymentStatus.PAY_COMPLETED))
            throw new CustomException(ErrorCode.DESPOSIT_ALREADY_PAID);


    }

    public DepositEntity getPaymentInProgressAndchangeStatusByOrderId(
        long depositId,
        PaymentStatus paymentStatus) {
        DepositEntity depositEntity = depositRepository.findById(depositId)
            .orElseThrow(() -> new CustomException(
                ErrorCode.DEPOSIT_NOT_EXSITING));

        depositEntity.setPaymentStatus(paymentStatus);

        return depositEntity;
    }

    public DepositEntity getPaymentCompletedAndChangeStatusByOrderId(
        long depositId,
        PaymentStatus paymentStatus) {
        DepositEntity depositEntity = depositRepository.findById(depositId)
            .orElseThrow(() -> new CustomException(
                ErrorCode.DEPOSIT_NOT_EXSITING));

        depositEntity.setPaymentStatus(paymentStatus);

        return depositEntity;
    }
}
