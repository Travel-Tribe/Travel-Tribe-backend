package com.zerobase.entity;

import com.zerobase.model.type.DepositStatus;
import com.zerobase.model.type.PaymentMethod;
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
public class DepositEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositId;
    private Long postId;
    private Long participationId;
    private String userId;
    // 후속 보충 데이터
    private String payKey;
    private Integer amount;
    private PaymentMethod paymentMethod;
    // 변경 데이터
    private DepositStatus depositStatus;
}
