package com.zerobase.travel.communities.service;

import com.zerobase.travel.communities.entity.CommunityEntity;
import com.zerobase.travel.communities.repository.CommunityRepository;
import com.zerobase.travel.communities.type.CommunityDto;
import com.zerobase.travel.communities.type.CustomException;
import com.zerobase.travel.communities.type.ErrorCode;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// todo : spring security ID 정보 추가할것
@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    public CommunityDto createPost(
        String title, String content, String userId) {

        CommunityEntity entity = communityRepository.
            save(
                CommunityEntity
                    .builder()
                    .userId(userId)
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

    // 포스트 8건 조회
    public Page<CommunityDto> getPosts(Pageable pageable) {
        Page<CommunityEntity> communityEntities = communityRepository.findAll(
            pageable);

        return communityEntities.map(CommunityDto::fromEntity);


    }




    public void deletePost(long communityId, String userId) {

        CommunityEntity communityEntity = communityRepository.findById(
            communityId).orElseThrow(()
            -> new CustomException(ErrorCode.COMMUNITY_NON_EXISTENT));

        if(!communityEntity.getUserId().equals(userId)) {
            throw new CustomException(ErrorCode.USER_UNAUTHORIZED_REQUEST);
        }

        communityRepository.deleteByCommunityId(communityId);
    }

    public CommunityDto updatePost(long communityId, String title, String content, String userId) {

        CommunityEntity communityEntity = communityRepository.findByCommunityId(
            communityId).orElseThrow(()
            -> new CustomException(ErrorCode.COMMUNITY_NON_EXISTENT));

        if(!Objects.equals(communityEntity.getUserId(), userId))
            throw new CustomException(ErrorCode.USER_UNAUTHORIZED_REQUEST);


        communityEntity.setTitle(title);
        communityEntity.setContent(content);

        return CommunityDto.fromEntity(
            communityRepository.save(communityEntity));
    }
}


