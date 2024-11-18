package com.zerobase.travel.communities.controller;

import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.communities.service.CommunityManagementService;
import com.zerobase.travel.communities.type.RequestCreateCommunity;
import com.zerobase.travel.communities.type.RequestPostCommunity;
import com.zerobase.travel.communities.type.ResponseCommunityDto;
import com.zerobase.travel.post.dto.response.PagedResponseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/communities")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityManagementService communityManagementService;


    // 새로운 커뮤니티 생성
    @PostMapping
    public ResponseEntity<ResponseMessage<ResponseCommunityDto>> createCommunity(
        @Valid @RequestBody
        RequestCreateCommunity request,
        @RequestHeader("X-User-Id") String userId) {

        return ResponseEntity.ok
            (ResponseMessage.success(
                communityManagementService.createPost(request, userId)));
    }

    // 커뮤니티 페이지 항상 최신순으로 조회
    @GetMapping
    public ResponseEntity<ResponseMessage<PagedResponseDTO<ResponseCommunityDto>>> getCommunities(
        @PageableDefault(size = 8, sort = "communityId", direction = Direction.DESC) Pageable pageable
    ) {

        return ResponseEntity.ok(ResponseMessage.success(
            communityManagementService.getPosts(pageable)));
    }

    // 커뮤니티 단건 조회
    @GetMapping(value = "/{communityId}")
    public ResponseEntity<ResponseMessage<ResponseCommunityDto>> getCommunity(
        @Min(1) @PathVariable long communityId) {
        return ResponseEntity.ok(ResponseMessage.success(
            communityManagementService.getPost(communityId)));

    }

    // 커뮤니티 삭제
    @DeleteMapping(value = "/{communityId}")
    public ResponseEntity<ResponseMessage<ResponseCommunityDto>> deleteCommunity(
        @Min(1) @PathVariable long communityId,
        @RequestHeader("X-User-Id") String userId) {
        communityManagementService.deletePost(communityId, userId);

        return ResponseEntity.ok(ResponseMessage.success());
    }

    // 커뮤니티 수정
    @PutMapping(value = "/{communityId}")
    public ResponseEntity<ResponseMessage<ResponseCommunityDto>> updateCommunity(
        @Valid @RequestBody RequestPostCommunity request,
        @RequestHeader("X-User-Id") String userId) {
        return ResponseEntity.ok(ResponseMessage.success(
            communityManagementService.updatePost(request, userId)));
    }


}
