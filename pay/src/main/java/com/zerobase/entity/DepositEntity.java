package com.zerobase.entity;

import com.zerobase.model.type.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
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
    private PaymentStatus paymentStatus;
}
