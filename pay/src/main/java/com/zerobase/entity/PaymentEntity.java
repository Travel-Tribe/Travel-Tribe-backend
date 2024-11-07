package com.zerobase.entity;

import com.zerobase.model.type.OrderType;
import com.zerobase.model.type.PGMethod;
import com.zerobase.model.type.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private String paykey;
    private String userId;
    private Long amount;
    // 결제라는 개념이 단순히 deposit에 종속된 개념이 아니라 deposit을 포함하는 상위개념으로
    // 취급하기 위해서 depositstatus 와 별개로 Paymentstatus 를 두고 관리함. 다른 아이템에 확장가능하도록.
    private PaymentStatus paymentStatus;
    private PGMethod pgMethod;

    // 여기서는 deposit
    private OrderType referencialOrderType;
    // 여기서는 deposit_id
    private Long referentialOrderId;

}
