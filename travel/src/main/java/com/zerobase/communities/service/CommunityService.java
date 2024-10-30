package com.zerobase.communities.service;

import com.zerobase.communities.entity.CommunityEntity;
import com.zerobase.communities.repository.CommunityRepository;
import com.zerobase.communities.type.CommunityDto;
import com.zerobase.communities.type.CustomException;
import com.zerobase.communities.type.ErrorCode;
import com.zerobase.typeCommon.Continent;
import com.zerobase.typeCommon.Country;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import org.springframework.stereotype.Service;

// todo : spring security ID 정보 추가할것
@Service
public class CommunityService {

    CommunityRepository communityRepository;

    public CommunityDto createPost(Continent continent,
        Country country, String region,
        String title, String content) {

        CommunityEntity entity = communityRepository.save(
            CommunityEntity
                .builder()
                .userId(123L)
                .continent(continent)
                .country(country)
                .region(region)
                .title(title)
                .content(content)
                .build()
        );

        return CommunityDto.fromEntity(entity);
    }

    // 포스트 단건 조회
    public CommunityDto getPost(Long communityId) {
        CommunityEntity communityEntity = communityRepository
            .findByCommunityId(communityId).orElseThrow(
                () -> new CustomException(ErrorCode.COMMUNITY_NON_EXISTENT));

        return CommunityDto.fromEntity(communityEntity);
    }

    // 포스트 다건 조회
    public List<CommunityDto> getPosts() {
        List<CommunityEntity> communityEntities = communityRepository.findAll();

        return communityEntities.stream().map(CommunityDto::fromEntity)
            .toList();


    }


    public void deletePost(long communityId) {
        communityRepository.deleteByCommunityId(communityId);
    }

    public CommunityDto updatePost(long communityId, Continent continent, Country country,
        String region, String title, String content) {

        if(!communityRepository.existsByCommunityId(communityId)){
            throw new CustomException(ErrorCode.COMMUNITY_NON_EXISTENT);
        }

        CommunityEntity entity = communityRepository.update(
            CommunityEntity
                .builder()
                .communityId(communityId)
                .userId(123L)
                .continent(continent)
                .country(country)
                .region(region)
                .title(title)
                .content(content)
                .build()
        );

        return CommunityDto.fromEntity(entity);
    }
}
