package com.zerobase.service;

import com.zerobase.entity.DepositEntity;
import com.zerobase.model.DepositDto;
import com.zerobase.model.exception.CustomException;
import com.zerobase.model.type.DepositStatus;
import com.zerobase.repository.DepositRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class DepositService {

    private final DepositRepository depositRepository;





    // depositId를 생성하기 위해서
    public DepositDto createDepositOrder(Long postId, Long participationId,
        String userId) {
        log.info("DepositRepository initial save start");

        DepositEntity depositEntity = DepositEntity.builder()
            .postId(postId)
            .participationId(participationId)
            .userId(userId)
            .depositStatus(DepositStatus.BEFORE_PAYMENT)
            .build();

        return DepositDto.fromEntity(depositRepository.save(depositEntity));
    }



/*
    public void changeStatusToCompleteByOrderId(Long referentialOrderId) {
        DepositEntity depositEntity = depositRepository.findById(
            referentialOrderId).orElseThrow(
            () -> new CustomException());

        depositEntity.setDepositStatus(DepositStatus.PAID);
    }

    public void changeStatusToFailByOrderId(Long referentialOrderId) {
        DepositEntity depositEntity = depositRepository.findById(
            referentialOrderId).orElseThrow(
            () -> new CustomException());

        depositEntity.setDepositStatus(DepositStatus.FAILED);
    }

    public DepositDto changeStatusToRefundedByParticipationId(Long participationId) {

        DepositEntity depositEntity = depositRepository.findByParticipationId(
            participationId).orElseThrow(() -> new CustomException());

        depositEntity.setDepositStatus(DepositStatus.REFUNDED);
    return null;
    }

 */
}
