package com.zerobase.travel.repository;

import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.type.ParticipationStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationRepository extends JpaRepository<ParticipationEntity,Long> {


    int countByUserIdAndParticipationStatusIn(String userId, List<ParticipationStatus> participationStatuses);

    List<ParticipationEntity> findAllByUserIdAndParticipationStatusIn(String userId, List<ParticipationStatus> participationStatuses);
  
    Optional<ParticipationEntity> findByPostEntityPostIdAndUserId(Long postId, String userId);

    List<ParticipationEntity> findAllByPostEntityPostIdAndParticipationStatusIn(Long postId,  List<ParticipationStatus> join);

    int countByPostEntityPostIdAndParticipationStatusIn(Long postId, List<ParticipationStatus> join);

    int countByParticipationStatusAndPostEntity(ParticipationStatus participationStatus, PostEntity postEntity);

    List<ParticipationEntity> findAllByPostEntityPostIdAndParticipationStatus(
        Long postId, ParticipationStatus participationStatus);


    List<ParticipationEntity> findAllByPostEntityIn(List<PostEntity> postEntities);

    List<ParticipationEntity> findAllByDepositReturnDate(LocalDate now);

    List<ParticipationEntity> findAllByParticipationStatusAndCreatedAtBefore(ParticipationStatus participationStatus, LocalDateTime localDateTime);
}