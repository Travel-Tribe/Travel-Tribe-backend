package com.zerobase.service;

import static com.zerobase.config.Constants.DEPOSIT_AMOUNT;

import com.zerobase.entity.PaymentEntity;
import com.zerobase.model.exception.CustomException;
import com.zerobase.model.type.OrderType;
import com.zerobase.model.type.PGMethod;
import com.zerobase.model.type.PaymentDto;
import com.zerobase.model.type.PaymentStatus;
import com.zerobase.repository.PaymentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository ;


    public PaymentDto createPayment(Long depositId, String userId, String payKey,
        PGMethod pgMethod) {
        log.info(" create payment servicea start ");

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

    public PaymentDto ChangeStatusToCompleteByUserId(String userId) {
        log.info("change status to complete by userId : {}", userId);

        PaymentEntity paymentEntity =
            paymentRepository.findByUserIdAndPaymentStatus(userId,PaymentStatus.PAY_IN_PROGRESS)
            .orElseThrow(() -> new CustomException());

        paymentEntity.setPaymentStatus(PaymentStatus.PAY_COMPLETED);
        paymentRepository.save(paymentEntity);

        return PaymentDto.fromEntity(paymentEntity);
    }

    public PaymentDto ChangeStatusToFailByUserId(String userId) {
        log.info("change status to fail by userId : {}", userId);
        PaymentEntity paymentEntity =
            paymentRepository.findByUserIdAndPaymentStatus(userId,PaymentStatus.PAY_IN_PROGRESS)
                .orElseThrow(() -> new CustomException());

        paymentEntity.setPaymentStatus(PaymentStatus.PAY_FAILED);
        paymentRepository.save(paymentEntity);

        return PaymentDto.fromEntity(paymentEntity);
    }

    public PaymentDto ChangeStatusToRefundedByOrderId(Long referencialOrderId) {
        log.info("change status to refunded by orderId : {}", referencialOrderId);
        PaymentEntity paymentEntity = paymentRepository
            .findByReferentialOrderId(referencialOrderId)
            .orElseThrow(() -> new CustomException());

        paymentEntity.setPaymentStatus(PaymentStatus.PAY_REFUNDED);
        paymentRepository.save(paymentEntity);

        return PaymentDto.fromEntity(paymentEntity);
    }

    public List<PaymentDto> getPayments(String userId) {
        return paymentRepository.findAllByUserId(userId).stream().map(
            PaymentDto::fromEntity).toList();
    }
}
