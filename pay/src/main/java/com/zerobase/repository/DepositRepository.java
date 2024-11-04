package com.zerobase.repository;

import com.zerobase.entity.DepositEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<DepositEntity,Long> {

    Optional<DepositEntity> findByParticipationId(Long participationId);
}