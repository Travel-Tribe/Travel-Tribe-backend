package com.zerobase.travel.communities.repository;

import com.zerobase.travel.communities.entity.CommunityEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {


    Optional<CommunityEntity> findByCommunityId(Long communityId);

    Page<CommunityEntity> findAll( Pageable pageable);

    void deleteByCommunityId(long communityId);


    Optional<CommunityEntity> findByCommunityId(long communityId);

    Optional<CommunityEntity> findByUserId(String userId);
}

