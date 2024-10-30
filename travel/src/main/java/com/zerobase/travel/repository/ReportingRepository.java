package com.zerobase.travel.repository;

import com.zerobase.travel.entity.ReportingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportingRepository extends JpaRepository<ReportingEntity, Long> {

    boolean existsByPostIdAndSenderUserIdAndReceiverUserId(long postId, long senderUserId, Long receiverUserId);
}
