package com.zerobase.model;

import com.zerobase.model.type.OrderType;
import com.zerobase.model.type.PGMethod;
import com.zerobase.model.type.PaymentDto;
import com.zerobase.model.type.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePaymentDto {

    private OrderType referencialOrderType;
    private Long referentialOrderId;
    private Long amount;
    private PaymentStatus paymentStatus;
    private PGMethod pgMethod;


    public static ResponsePaymentDto fromDto(PaymentDto paymentDto) {
        return ResponsePaymentDto.builder()
            .referencialOrderType(paymentDto.getReferencialOrderType())
            .referentialOrderId(paymentDto.getReferentialOrderId())
            .amount(paymentDto.getAmount())
            .pgMethod(paymentDto.getPgMethod())
            .paymentStatus(paymentDto.getPaymentStatus())
            .build();


    }
}
