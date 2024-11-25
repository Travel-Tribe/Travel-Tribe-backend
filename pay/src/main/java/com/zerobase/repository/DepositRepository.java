package com.zerobase.repository;

import com.zerobase.entity.DepositEntity;
import com.zerobase.model.type.PaymentStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<DepositEntity,Long> {

    Optional<DepositEntity> findByParticipationIdAndPaymentStatus(Long participationId,
        PaymentStatus payCompleted);

    boolean existsByParticipationIdAndPaymentStatus(long participationId,
        PaymentStatus paymentStatus);
}
