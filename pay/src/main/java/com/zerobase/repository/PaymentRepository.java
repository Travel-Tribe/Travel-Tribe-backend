package com.zerobase.repository;

import com.zerobase.entity.PaymentEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {

    Optional<PaymentEntity> findByPaykey(String tid);

    Optional<PaymentEntity> findByReferentialOrderId(Long referentialOrderId);


}
