package com.zerobase.travel.controller;

import com.zerobase.travel.service.CommunityManagementService;
import com.zerobase.travel.dto.request.RequestCreateCommunity;
import com.zerobase.travel.dto.request.RequestPostCommunity;
import com.zerobase.travel.dto.response.ResponseCommunityDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/communities")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityManagementService communityManagementService;


    // 새로운 커뮤니티 생성
    @PostMapping
    public ResponseEntity<ResponseCommunityDto> createCommunity(
        @Valid @RequestBody
        RequestCreateCommunity request) {

        return ResponseEntity.ok
            (communityManagementService.createPost(request));
    }

    // 커뮤니티 페이지 항상 최신순으로 조회
    @GetMapping
    public ResponseEntity<Page<ResponseCommunityDto>> getCommunities(
        Pageable pageable) {

        return ResponseEntity.ok(communityManagementService.getPosts(pageable));
    }

    // 커뮤니티 단건 조회
    @GetMapping(value = "/{communityId}")
    public ResponseEntity<ResponseCommunityDto> getCommunity(
        @Min(1) @PathVariable long communityId) {
        return ResponseEntity.ok(
            communityManagementService.getPost(communityId));

    }

    // 커뮤니티 삭제
    @DeleteMapping(value = "/{communityId}")
    public ResponseEntity<ResponseCommunityDto> deleteCommunity(
        @Min(1) @PathVariable long communityId) {
        communityManagementService.deletePost(communityId);

        return null;
    }

    // 커뮤니티 수정
    @PutMapping(value = "/{communityId}")
    public ResponseEntity<ResponseCommunityDto> updateCommunity(
        @Valid @RequestBody RequestPostCommunity request) {
        return ResponseEntity.ok(
            communityManagementService.updatePost(request));
    }


}
