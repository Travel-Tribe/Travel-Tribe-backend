package com.zerobase.travel.repository.specification;

import com.zerobase.travel.entity.MinusRatingEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MinusRatingRepository extends JpaRepository<MinusRatingEntity, Long> {

    List<MinusRatingEntity> findAllByReceiverUserId(Long userId);
}
