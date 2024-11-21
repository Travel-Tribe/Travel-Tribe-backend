package com.zerobase.service;

import com.zerobase.config.Constants;
import com.zerobase.entity.PaymentEntity;
import com.zerobase.exception.BizException;
import com.zerobase.exception.errorCode.PaymentErrorCode;
import com.zerobase.model.PaymentDto;
import com.zerobase.model.type.OrderType;
import com.zerobase.model.type.PGMethod;
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
    private final Constants constants;


    public PaymentDto createPaymentAndSave(Long depositId, String userId, String payKey,
        PGMethod pgMethod) {
        log.info(" create payment servicea start ");

        PaymentEntity paymentEntity = paymentRepository.save(
            PaymentEntity.builder()
                .referentialOrderType(OrderType.DEPOSIT)
                .referentialOrderId(depositId)
                .userId(userId)
                .paykey(payKey)
                .amount(constants.DEPOSIT_AMOUNT)
                .paymentStatus(PaymentStatus.PAY_IN_PROGRESS)
                .pgMethod(pgMethod)
                .build());

        return PaymentDto.fromEntity(paymentEntity);

    }


    public PaymentEntity changeStatus(PaymentEntity paymentEntity,
        PaymentStatus status) {
        paymentEntity.setPaymentStatus(status);
        return paymentEntity;
    }

    public List<PaymentDto> getPaymentsByUserId(String userId) {
        return paymentRepository.findAllByUserId(userId).stream().map(
            PaymentDto::fromEntity).toList();
    }

    public PaymentEntity getPaymentsInProgressAndChangeStatusByOrderId(
        long depositId, PaymentStatus paymentStatus) {

        PaymentEntity paymentEntity = paymentRepository.findByReferentialOrderIdAndPaymentStatus(
            depositId, PaymentStatus.PAY_IN_PROGRESS).orElseThrow(() -> new BizException(
            PaymentErrorCode.PAYMENT_NOT_EXSITING));

        paymentEntity.setPaymentStatus(paymentStatus);


        return paymentEntity;
    }

    public PaymentEntity getPaymentsInPayCompletedByOrderId( String userId,long orderId) {
        PaymentEntity paymentEntity = paymentRepository.findByReferentialOrderIdAndPaymentStatus(
            orderId, PaymentStatus.PAY_COMPLETED).orElseThrow(() -> new BizException(
            PaymentErrorCode.PAYMENT_NOT_EXSITING));

        if(!Objects.equals(paymentEntity.getUserId(), userId)) throw new BizException(
            PaymentErrorCode.INVALID_PARTICIPATION_INFORMATION);

        return paymentEntity;
    }



    public void save(PaymentEntity paymentEntity) {
         paymentRepository.save(paymentEntity);
    }
}
