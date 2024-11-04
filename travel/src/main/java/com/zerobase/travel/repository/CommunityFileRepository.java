package com.zerobase.travel.repository;

import com.zerobase.travel.entity.CommunityFileEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityFileRepository extends JpaRepository<CommunityFileEntity, Long> {

    List<CommunityFileEntity> findAllByCommunityEntity_CommunityId(long communityId);

    void deleteAllByCommunityEntity_CommunityId(long communityId);
}
