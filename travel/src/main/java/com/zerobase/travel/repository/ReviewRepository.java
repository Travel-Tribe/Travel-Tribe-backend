package com.zerobase.travel.repository;

import com.zerobase.travel.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    boolean existsByUserIdAndPostId(long userId, long postId);

    boolean existsByIdAndUserId(long reviewId, long userId);

    boolean existsByIdAndPostId(long reviewId, long postId);
}
