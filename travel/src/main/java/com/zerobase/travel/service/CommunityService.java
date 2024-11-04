package com.zerobase.travel.service;

import com.zerobase.travel.entity.CommunityEntity;
import com.zerobase.travel.repository.CommunityRepository;
import com.zerobase.travel.dto.CommunityDto;
import com.zerobase.travel.communities.type.CustomException;
import com.zerobase.travel.communities.type.ErrorCode;
import com.zerobase.travel.type.Continent;
import com.zerobase.travel.type.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// todo : spring security ID 정보 추가할것
@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    public CommunityDto createPost(Continent continent,
        Country country, String region,
        String title, String content) {

        CommunityEntity entity = communityRepository.
            save(
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
    public Page<CommunityDto> getPosts(Pageable pageable) {
        Page<CommunityEntity> communityEntities = communityRepository.findAll(
            pageable);

        return communityEntities.map(CommunityDto::fromEntity);


    }




    public void deletePost(long communityId) {
        communityRepository.deleteByCommunityId(communityId);
    }

    public CommunityDto updatePost(long communityId, Continent continent,
        Country country,
        String region, String title, String content) {

        CommunityEntity communityEntity = communityRepository.findByCommunityId(
            communityId).orElseThrow(()
            -> new CustomException(ErrorCode.COMMUNITY_NON_EXISTENT));

        communityEntity.setContinent(continent);
        communityEntity.setCountry(country);
        communityEntity.setRegion(region);
        communityEntity.setTitle(title);
        communityEntity.setContent(content);

        return CommunityDto.fromEntity(
            communityRepository.save(communityEntity));
    }
}

