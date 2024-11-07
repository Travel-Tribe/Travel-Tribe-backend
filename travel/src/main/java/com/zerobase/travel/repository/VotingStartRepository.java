package com.zerobase.travel.repository;

import com.zerobase.travel.entity.VotingStartEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingStartRepository extends JpaRepository<VotingStartEntity, Long> {

    Optional<VotingStartEntity> findByPostId(long postId);

    boolean existsByPostId(long postId);

    boolean existsByIdAndPostId(long votingStartsId, long postId);
}
