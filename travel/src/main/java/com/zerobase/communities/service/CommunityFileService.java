package com.zerobase.communities.service;

import com.zerobase.communities.entity.CommunityEntity;
import com.zerobase.communities.entity.CommunityFileEntity;
import com.zerobase.communities.repository.CommunityFileRepository;
import com.zerobase.communities.type.CommunityFileDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityFileService {

    private final CommunityFileRepository communityFileRepository;


    List<CommunityFileDto> getFiles(Long communityId) {
        return communityFileRepository
            .findAllByCommunityEntity_CommunityId(communityId)
            .stream().map(
                CommunityFileDto::fromEntity).toList();
    }

    ;

    List<CommunityFileDto> saveFiles(Long communityId, List<String> fileNames) {

        List<CommunityFileEntity> communityFileEntities = fileNames.stream().map(e ->
                CommunityFileEntity
                    .builder()
                    .communityEntity(
                        CommunityEntity.builder().communityId(communityId).build())
                    .fileName(e)
                    .build())
            .toList();

       return communityFileRepository.saveAll(communityFileEntities)
            .stream().map(CommunityFileDto::fromEntity).toList();
    }


    void deleteAllByCommunityId(long communityId) {
        communityFileRepository.deleteAllByCommunityEntity_CommunityId(communityId);
    }
}
