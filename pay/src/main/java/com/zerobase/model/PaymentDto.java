package com.zerobase.model;

import com.zerobase.entity.PaymentEntity;
import com.zerobase.model.type.OrderType;
import com.zerobase.model.type.PGMethod;
import com.zerobase.model.type.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class PaymentDto {
    private Long paymentId;
    private OrderType referencialOrderType;
    private Long referentialOrderId;
    private String userId;
    private String payKey;
    private Long amount;
    private PaymentStatus paymentStatus;
    private PGMethod pgMethod;

    public static PaymentDto fromEntity(PaymentEntity paymentEntity) {
        return PaymentDto.builder()
            .paymentId(paymentEntity.getPaymentId())
            .referencialOrderType(paymentEntity.getReferentialOrderType())
            .referentialOrderId(paymentEntity.getReferentialOrderId())
            .userId(paymentEntity.getUserId())
            .amount(paymentEntity.getAmount())
            .pgMethod(paymentEntity.getPgMethod())
            .paymentStatus(paymentEntity.getPaymentStatus())
            .build();


    }
}
