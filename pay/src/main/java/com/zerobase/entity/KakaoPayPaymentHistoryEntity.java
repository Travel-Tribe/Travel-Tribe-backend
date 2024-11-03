package com.zerobase.entity;

import com.zerobase.model.type.KaKaoPayStatus;
import com.zerobase.model.type.PayMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
    private KaKaoPayStatus kaKaoStatus;
    private PayMethod payMethod;
    private Long amount;
}
