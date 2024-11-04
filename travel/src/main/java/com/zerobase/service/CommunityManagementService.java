package com.zerobase.service;

import com.zerobase.model.CommunityDto;
import com.zerobase.model.CommunityFileDto;
import com.zerobase.model.RequestCreateCommunity;
import com.zerobase.model.RequestPostCommunity;
import com.zerobase.model.ResponseCommunityDto;
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


    // 단건 포스트 생성
    @Transactional
    public ResponseCommunityDto createPost(RequestCreateCommunity request) {

        // request에서 필요한 정보만 넘겨서 post생성
        CommunityDto communityDto = communityService.createPost(
            request.getContinent(),request.getCountry(),request.getRegion(),
            request.getTitle(),request.getContent());

        // 부모 테이블에서 communityId를 추출하고 file list 정보를 추출하여 첨부파일 맵핑
        List<CommunityFileDto> communityFileDtos
            = communityFileService.saveFiles(communityDto.getCommunityId(),request.getFiles());

        return ResponseCommunityDto.fromEntity(communityDto, communityFileDtos);
    }

    //다건 조회 ; 페이징처리
    public Page<ResponseCommunityDto> getPosts(Pageable pageable) {

        // 페이징에 따른 포스트 리스트 조회
        Page<CommunityDto> communityDtos = communityService.getPosts(pageable);

        // 포스트 리스트에 해당하는 첨부파일 리스트를 조회
        Page<ResponseCommunityDto> responseCommunityDtos = communityDtos.map(e
            -> ResponseCommunityDto.fromEntity(e,
            communityFileService.getFiles(e.getCommunityId())));

         return responseCommunityDtos;
    }

    // 단건조회
    public ResponseCommunityDto getPost(long communityId) {
        // 단건에 대한 포스트 조회
        CommunityDto communityDto = communityService.getPost(communityId);
       // 포스트에 해당하는 첨부파일 리스트 조회
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

    // 단건 업데이트
    @Transactional
    public ResponseCommunityDto updatePost( RequestPostCommunity request) {

        // request에서 필요한 정보만 넘겨서 post업데이트
        CommunityDto communityDto = communityService.updatePost(request.getCommunityId(),
            request.getContinent(),request.getCountry(),request.getRegion(),
            request.getTitle(),request.getContent());

        // post에 관련되어 저장되어있던 파일리스트 전체 삭제
        communityFileService.deleteAllByCommunityId(request.getCommunityId());

        // communityId와 file list 정보를 추출하여 새로 올라온 첨부파일 맵핑
        List<CommunityFileDto> communityFileDtos
            = communityFileService.saveFiles(communityDto.getCommunityId(),request.getFiles());

        return ResponseCommunityDto.fromEntity(communityDto, communityFileDtos);
    }

}

