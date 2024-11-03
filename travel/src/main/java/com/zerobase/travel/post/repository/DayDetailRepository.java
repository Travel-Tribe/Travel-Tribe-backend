package com.zerobase.travel.post.repository;

import com.zerobase.travel.post.entity.DayDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayDetailRepository extends JpaRepository<DayDetailEntity, Long> {
    List<DayDetailEntity> findAllByDayDayId(Long dayId);
}
