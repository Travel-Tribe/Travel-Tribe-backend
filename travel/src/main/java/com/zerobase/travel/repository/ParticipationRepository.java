package com.zerobase.travel.repository;

import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.type.ParticipationStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationRepository extends JpaRepository<ParticipationEntity,Long> {


    int countByParticipationStatusAndUserId(ParticipationStatus participationStatus, String userId);
  
    Optional<ParticipationEntity> findByPostEntityPostIdAndUserId(Long postId, String userId);

    List<ParticipationEntity> findAllByPostEntityPostIdAndParticipationStatus(Long postId, ParticipationStatus participationStatus);



}