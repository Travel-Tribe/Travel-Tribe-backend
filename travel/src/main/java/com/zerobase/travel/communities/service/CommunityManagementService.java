package com.zerobase.travel.communities.service;

import com.zerobase.travel.communities.type.CommunityDto;
import com.zerobase.travel.communities.type.CommunityFileDto;
import com.zerobase.travel.communities.type.RequestCreateCommunity;
import com.zerobase.travel.communities.type.RequestUpdateCommunity;
import com.zerobase.travel.communities.type.ResponseCommunityDto;
import com.zerobase.travel.post.dto.response.PagedResponseDTO;
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
    public ResponseCommunityDto createPost(RequestCreateCommunity request,
        String userId) {

        CommunityDto communityDto = communityService.createPost(
            request.getTitle(),request.getContent(),userId);

        List<CommunityFileDto> communityFileDtos
            = communityFileService.saveFiles(communityDto.getCommunityId(),request.getFiles());

        return ResponseCommunityDto.fromEntity(communityDto, communityFileDtos);
    }

    //다건 조회
    public PagedResponseDTO<ResponseCommunityDto> getPosts(Pageable pageable) {


        Page<CommunityDto> communityDtos = communityService.getPosts(pageable);

        Page<ResponseCommunityDto> responseCommunityDtos = communityDtos.map(e
            -> ResponseCommunityDto.fromEntity(e,
            communityFileService.getFiles(e.getCommunityId())));

        return ConvertPageToPagedResponse(
            responseCommunityDtos);
    }

    private static PagedResponseDTO<ResponseCommunityDto> ConvertPageToPagedResponse(
        Page<ResponseCommunityDto> responseCommunityDtos) {

        // PagedResponseDTO로 변환
        PagedResponseDTO<ResponseCommunityDto> pagedResponse
            = PagedResponseDTO.<ResponseCommunityDto>builder()
            .content(responseCommunityDtos.getContent())
            .pageNumber(responseCommunityDtos.getNumber())
            .pageSize(responseCommunityDtos.getSize())
            .totalElements(responseCommunityDtos.getTotalElements())
            .totalPages(responseCommunityDtos.getTotalPages())
            .last(responseCommunityDtos.isLast())
            .build();

        return pagedResponse;
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
    public void deletePost(long communityId, String userId) {
        communityService.deletePost(communityId,userId);
        communityFileService.deleteAllByCommunityId(communityId);


    }

    @Transactional
    public ResponseCommunityDto updatePost( RequestUpdateCommunity request,
        String userId) {


        CommunityDto communityDto = communityService.updatePost(request.getCommunityId(),
            request.getTitle(),request.getContent(),userId);

        communityFileService.deleteAllByCommunityId(request.getCommunityId());

        List<CommunityFileDto> communityFileDtos
            = communityFileService.saveFiles(communityDto.getCommunityId(),request.getFiles());

        return ResponseCommunityDto.fromEntity(communityDto, communityFileDtos);
    }

}

