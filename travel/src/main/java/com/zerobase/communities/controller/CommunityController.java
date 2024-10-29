package com.zerobase.communities.controller;

import com.zerobase.communities.type.ResponseCommunityDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/communities")
public class CommunityController {


    // 새로운 커뮤니티 생성
    @PostMapping
    public ResponseEntity<ResponseCommunityDto> createCommunity() {
        return null;
    }

    // 커뮤니티 리스트 조회
    @GetMapping
    public ResponseEntity<List<ResponseCommunityDto>> getCommunities() {
        return null;
    }

    // 커뮤니티 상세 조회
    @GetMapping(value="/{communityId}")
    public ResponseEntity<ResponseCommunityDto> getCommunity(
        @PathVariable long communityId) {
        return null;
    }

    // 커뮤니티 삭제
    @DeleteMapping(value="/{communityId}")
    public ResponseEntity<ResponseCommunityDto> deleteCommunity(
        @PathVariable long communityId) {
        return null;
    }

    // 커뮤니티 수정
    @PatchMapping(value="/{communityId}")
    public ResponseEntity<ResponseCommunityDto> updateCommunity(
        @PathVariable long communityId) {
        return null;
    }


}
