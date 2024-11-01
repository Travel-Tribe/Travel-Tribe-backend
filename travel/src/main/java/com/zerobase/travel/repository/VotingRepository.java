package com.zerobase.travel.repository;

import com.zerobase.travel.entity.VotingEntity;
import com.zerobase.travel.entity.VotingStartEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingRepository extends JpaRepository<VotingEntity, Long> {

    List<VotingEntity> findAllByVotingStartEntity(VotingStartEntity votingStartsId);

    boolean existsByUserIdAndVotingStartEntity(long userId, VotingStartEntity entity);
}
