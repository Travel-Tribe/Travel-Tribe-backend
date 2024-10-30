package com.zerobase.communities.controller;

import com.zerobase.communities.service.CommunityManagementService;
import com.zerobase.communities.type.RequestPostCommunity;
import com.zerobase.communities.type.ResponseCommunityDto;
import com.zerobase.communities.type.RequestCreateCommunity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ResponseCommunityDto> createCommunity(@Valid @RequestBody
    RequestCreateCommunity request) {

        return ResponseEntity.ok
            (communityManagementService.createPost(request));
    }

    // 커뮤니티 리스트 조회
    @GetMapping
    public ResponseEntity<List<ResponseCommunityDto>> getCommunities() {
        return ResponseEntity.ok( communityManagementService.getPosts());
    }

    // 커뮤니티 단건 조회
    @GetMapping(value = "/{communityId}")
    public ResponseEntity<ResponseCommunityDto> getCommunity(
        @Min(1) @PathVariable long communityId) {
        return ResponseEntity.ok(communityManagementService.getPost(communityId));

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
       return ResponseEntity.ok(communityManagementService.updatePost(request));
    }


}
