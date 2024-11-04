package com.zerobase.travel.service;

import com.zerobase.travel.communities.type.RequestCreateCommunity;
import com.zerobase.travel.communities.type.RequestPostCommunity;
import com.zerobase.travel.communities.type.ResponseCommunityDto;
import com.zerobase.travel.dto.CommunityDto;
import com.zerobase.travel.dto.CommunityFileDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityManagementService {

    private final CommunityService communityService;
    private final CommunityFileService communityFileService;


    // 포스트 생성
    @Transactional
    public ResponseCommunityDto createPost(RequestCreateCommunity request) {

        CommunityDto communityDto = communityService.createPost(
            request.getContinent(),request.getCountry(),request.getRegion(),
            request.getTitle(),request.getContent());

        List<CommunityFileDto> communityFileDtos
            = communityFileService.saveFiles(communityDto.getCommunityId(),request.getFiles());

        return ResponseCommunityDto.fromEntity(communityDto, communityFileDtos);
    }

    //다건 조회
    public Page<ResponseCommunityDto> getPosts(Pageable pageable) {

        Page<CommunityDto> communityDtos = communityService.getPosts(pageable);

        Page<ResponseCommunityDto> responseCommunityDtos = communityDtos.map(e
            -> ResponseCommunityDto.fromEntity(e,
            communityFileService.getFiles(e.getCommunityId())));

         return responseCommunityDtos;
    }

    // 단건조회
    public ResponseCommunityDto getPost(long communityId) {
        CommunityDto communityDto = communityService.getPost(communityId);
        List<CommunityFileDto> communityFileDtos = communityFileService.getFiles(
            communityId);

        return ResponseCommunityDto.fromEntity(communityDto, communityFileDtos);

    }

    //단건 삭제
    @Transactional
    public void deletePost(long communityId) {
        communityService.deletePost(communityId);
        communityFileService.deleteAllByCommunityId(communityId);


    }

    @Transactional
    public ResponseCommunityDto updatePost( RequestPostCommunity request) {

        CommunityDto communityDto = communityService.updatePost(request.getCommunityId(),
            request.getContinent(),request.getCountry(),request.getRegion(),
            request.getTitle(),request.getContent());

        communityFileService.deleteAllByCommunityId(request.getCommunityId());

        List<CommunityFileDto> communityFileDtos
            = communityFileService.saveFiles(communityDto.getCommunityId(),request.getFiles());

        return ResponseCommunityDto.fromEntity(communityDto, communityFileDtos);
    }

}

