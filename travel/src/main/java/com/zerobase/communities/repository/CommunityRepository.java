package com.zerobase.communities.repository;

import com.zerobase.communities.entity.CommunityEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {

     
    CommunityEntity save(CommunityEntity entity);


    Optional<CommunityEntity> findByCommunityId(Long communityId);

    Page<CommunityEntity> findAll(Long communityId, Pageable pageable);

    void deleteByCommunityId(long communityId);

    CommunityEntity update(CommunityEntity communityEntity);

    boolean existsByCommunityId(long communityId);
}
