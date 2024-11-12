package com.zerobase.model;

import com.zerobase.model.PaymentDto.PaymentDto;
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

    private String referencialOrderType;
    private Long referentialOrderId;
    private Long amount;
    private String paymentStatus;
    private String pgMethod;


    public static ResponsePaymentDto fromDto(PaymentDto paymentDto) {
        return ResponsePaymentDto.builder()
            .referencialOrderType(paymentDto.getReferencialOrderType().getKorean())
            .referentialOrderId(paymentDto.getReferentialOrderId())
            .amount(paymentDto.getAmount())
            .pgMethod(paymentDto.getPgMethod().getKorean())
            .paymentStatus(paymentDto.getPaymentStatus().getKorean())
            .build();


    }
}
