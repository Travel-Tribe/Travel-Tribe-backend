package com.zerobase.communities.service;

import com.zerobase.communities.type.CommunityDto;
import com.zerobase.communities.type.CommunityFileDto;
import com.zerobase.communities.type.RequestPostCommunity;
import com.zerobase.communities.type.ResponseCommunityDto;
import com.zerobase.communities.type.RequestCreateCommunity;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    public List<ResponseCommunityDto> getPosts() {
        List<CommunityDto> communityDtos = communityService.getPosts();

        List<ResponseCommunityDto> responseCommunityDtos = new ArrayList<>();

        for (CommunityDto communityDto : communityDtos) {
            List<CommunityFileDto> communityFileDtos
                = communityFileService.getFiles(communityDto.getCommunityId());

            responseCommunityDtos.add(
                ResponseCommunityDto.fromEntity(communityDto, communityFileDtos));
        }

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

