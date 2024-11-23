package com.zerobase.entity;

import com.zerobase.model.type.OrderType;
import com.zerobase.model.type.PGMethod;
import com.zerobase.model.type.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private String paykey;
    private String userId;
    private Long amount;
    // 결제라는 개념이 단순히 deposit에 종속된 개념이 아니라 deposit을 포함하는 상위개념으로
    // 취급하기 위해서 depositstatus 와 별개로 Paymentstatus 를 두고 관리함. 다른 아이템에 확장가능하도록.
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.STRING)
    private PGMethod pgMethod;

    // 여기서는 deposit
    @Enumerated(EnumType.STRING)
    private OrderType referentialOrderType;
    // 여기서는 deposit_id
    private Long referentialOrderId;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
