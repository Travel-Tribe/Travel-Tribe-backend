package com.zerobase.travel.repository;

import com.zerobase.travel.entity.RatingEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

    boolean existsByPostIdAndSenderUserIdAndReceiverUserId(long postId, long senderUserId, long receiverUserId);

    @Query(
        value = " select avg(rating.score) "
            + " from RatingEntity rating"
            + " where rating.receiverUserId = :userId "
    )
    Double getAvgReceiverRating(long userId);

    List<RatingEntity> findByReceiverUserId(Long userId);
}
