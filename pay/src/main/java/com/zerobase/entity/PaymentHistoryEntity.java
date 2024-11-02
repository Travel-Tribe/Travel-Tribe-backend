package com.zerobase.entity;

import com.zerobase.model.type.PaymentMethod;
import com.zerobase.model.type.PaymentStatus;
import com.zerobase.model.type.TransactionType;
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
public class PaymentHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentHistoryId;
    private Long userId;
    private Long amount;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;
    // 여기서는 deposit
    private TransactionType referencialTransactionType;
    // 여기서는 현재, deposit_id
    private Long referencialOrderId;

}
