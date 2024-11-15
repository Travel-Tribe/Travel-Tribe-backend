package com.zerobase.entity;

import com.zerobase.model.type.KaKaoPayStatus;
import com.zerobase.model.type.PayMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaoPayPaymentHistoryEntity {

    @Id
    private Long KakaoPayPaymentHistoryId;
    private Long paymentId;
    private Long userId;
    private String tid;
    private String partnerOrderId;
    private String partnerUserId;
    @Enumerated(EnumType.STRING)
    private KaKaoPayStatus kaKaoStatus;
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;
    private Long amount;
}
