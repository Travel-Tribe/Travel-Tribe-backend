package com.zerobase.service;

import static com.zerobase.config.Constants.DEPOSIT_AMOUNT;

import com.zerobase.entity.PaymentEntity;
import com.zerobase.model.exception.CustomException;
import com.zerobase.model.type.OrderType;
import com.zerobase.model.type.PGMethod;
import com.zerobase.model.type.PaymentDto;
import com.zerobase.model.type.PaymentStatus;
import com.zerobase.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository ;


    public PaymentDto createPayment(Long depositId, String userId, String payKey,
        PGMethod pgMethod) {

        PaymentEntity paymentEntity = paymentRepository.save(
            PaymentEntity.builder()
                .referencialOrderType(OrderType.DEPOSIT)
                .referentialOrderId(depositId)
                .userId(userId)
                .paykey(payKey)
                .amount(DEPOSIT_AMOUNT)
                .paymentStatus(PaymentStatus.PAY_IN_PROGRESS)
                .pgMethod(pgMethod)
                .build());

        return PaymentDto.fromEntity(paymentEntity);

    }

    public PaymentDto ChangeStatusToCompleteByTid(String payKey) {

        PaymentEntity paymentEntity = paymentRepository.findByPaykey(payKey)
            .orElseThrow(() -> new CustomException());

        paymentEntity.setPaymentStatus(PaymentStatus.PAY_COMPLETED);
        paymentRepository.save(paymentEntity);

        return PaymentDto.fromEntity(paymentEntity);
    }

    public PaymentDto ChangeStatusToFailByTid(String payKey) {

        PaymentEntity paymentEntity = paymentRepository.findByPaykey(payKey)
            .orElseThrow(() -> new CustomException());

        paymentEntity.setPaymentStatus(PaymentStatus.PAY_FAILED);
        paymentRepository.save(paymentEntity);

        return PaymentDto.fromEntity(paymentEntity);
    }

    public PaymentDto ChangeStatusToRefundedByOrderId(Long referencialOrderId) {
        PaymentEntity paymentEntity = paymentRepository
            .findByReferentialOrderId(referencialOrderId)
            .orElseThrow(() -> new CustomException());

        paymentEntity.setPaymentStatus(PaymentStatus.PAY_REFUNDED);
        paymentRepository.save(paymentEntity);

        return PaymentDto.fromEntity(paymentEntity);
    }
}
