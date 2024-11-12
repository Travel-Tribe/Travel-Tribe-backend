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
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository ;


    public PaymentDto createPaymentAndSave(Long depositId, String userId, String payKey,
        PGMethod pgMethod) {
        log.info(" create payment servicea start ");

        PaymentEntity paymentEntity = paymentRepository.save(
            PaymentEntity.builder()
                .referentialOrderType(OrderType.DEPOSIT)
                .referentialOrderId(depositId)
                .userId(userId)
                .paykey(payKey)
                .amount(DEPOSIT_AMOUNT)
                .paymentStatus(PaymentStatus.PAY_IN_PROGRESS)
                .pgMethod(pgMethod)
                .build());

        return PaymentDto.fromEntity(paymentEntity);

    }


    public PaymentEntity changeStatus(PaymentEntity paymentEntity,
        PaymentStatus payFailed) {
        paymentEntity.setPaymentStatus(payFailed);
        return paymentEntity;
    }

    public List<PaymentDto> getPaymentsByUserId(String userId) {
        return paymentRepository.findAllByUserId(userId).stream().map(
            PaymentDto::fromEntity).toList();
    }

    public PaymentEntity getPaymentsInProgressByOrderId( String userId,long orderId) {
        PaymentEntity paymentEntity = paymentRepository.findByReferentialOrderIdAndPaymentStatus(
            orderId, PaymentStatus.PAY_IN_PROGRESS).orElseThrow(() -> new CustomException());

        if(!Objects.equals(paymentEntity.getUserId(), userId)) throw new CustomException();

        return paymentEntity;
    }

    public PaymentEntity getPaymentsInPayCompletedByOrderId( String userId,long orderId) {
        PaymentEntity paymentEntity = paymentRepository.findByReferentialOrderIdAndPaymentStatus(
            orderId, PaymentStatus.PAY_COMPLETED).orElseThrow(() -> new CustomException());

        if(!Objects.equals(paymentEntity.getUserId(), userId)) throw new CustomException();

        return paymentEntity;
    }



    public void savePayments(PaymentEntity paymentEntity) {
         paymentRepository.save(paymentEntity);
    }
}
