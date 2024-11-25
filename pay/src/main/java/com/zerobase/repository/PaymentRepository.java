package com.zerobase.repository;

import com.zerobase.entity.PaymentEntity;
import com.zerobase.model.type.PaymentStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {

    Optional<PaymentEntity> findByPaykey(String tid);

    Optional<PaymentEntity> findByReferentialOrderIdAndPaymentStatus(long referentialOrderId,
        PaymentStatus payCompleted);

    Optional<PaymentEntity> findByReferentialOrderId(long referentialOrderId);




    Optional<PaymentEntity> findByUserIdAndPaymentStatus(String usderId, PaymentStatus paymentStatus);

    List<PaymentEntity> findAllByUserId(String userId);

}
