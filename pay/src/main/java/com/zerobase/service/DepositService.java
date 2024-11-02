package com.zerobase.service;

import static com.zerobase.config.Constants.DEPOSIT_AMOUNT;

import com.zerobase.entity.DepositEntity;
import com.zerobase.model.DepositDto;
import com.zerobase.model.DepositInitialDto;
import com.zerobase.model.exception.CustomException;
import com.zerobase.model.type.DepositStatus;
import com.zerobase.model.type.PaymentMethod;
import com.zerobase.repository.DepositRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class DepositService {

    private final DepositRepository depositRepository;





    // depositId를 생성하기 위해서
    public DepositInitialDto initialSave(Long postId, Long participationId,
        String userId, String payKey, PaymentMethod paymentMethod) {
        log.info("DepositRepository initial save start");

        DepositEntity depositEntity = DepositEntity.builder()
            .postId(postId)
            .participationId(participationId)
            .userId(userId)
            .paymentMethod(paymentMethod)
            .payKey(payKey)
            .amount(DEPOSIT_AMOUNT)
            .depositStatus(DepositStatus.IN_PROGRESS)
            .build();

        return DepositInitialDto.fromEntity(depositRepository.save(depositEntity));
    }

    public DepositDto updateAfterPaySuccess(Long depositId, String payKey) {

        DepositEntity depositEntity = depositRepository.findById(depositId)
            .orElseThrow(() -> new CustomException());

        depositEntity.setPayKey(payKey);
        depositEntity.setDepositStatus(DepositStatus.PAID);

        return DepositDto.fromEntity(depositRepository.save(depositEntity));
    }


}
