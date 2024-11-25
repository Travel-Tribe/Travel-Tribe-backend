package com.zerobase.travel.post.repository;

import com.zerobase.travel.post.entity.ItineraryVisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItineraryVisitRepository extends JpaRepository<ItineraryVisitEntity, Long> {
    List<ItineraryVisitEntity> findAllByDayDayId(Long dayId);
}
