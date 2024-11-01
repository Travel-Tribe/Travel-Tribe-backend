package com.zerobase.travel.repository;

import com.zerobase.travel.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

    boolean existsByPostIdAndSenderUserIdAndReceiverUserId(long postId, long senderUserId, long receiverUserId);
}
