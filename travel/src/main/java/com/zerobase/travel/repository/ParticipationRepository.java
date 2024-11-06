package com.zerobase.travel.repository;

import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.type.ParticipationStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationRepository extends JpaRepository<ParticipationEntity,Long> {

    List<ParticipationEntity> findByPostEntityPostIdAndParticipationStatus(Long postId, ParticipationStatus participationStatuse);

    int countByParticipationStatusAndUserId(ParticipationStatus participationStatus, String userId);
}