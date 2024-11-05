package com.zerobase.travel.controller;

import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.service.ParticipationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "api/v1/posts/")
@RestController
@RequiredArgsConstructor
public class ParticipationController {

    private final ParticipationService participationService;

    @PostMapping("{postId}/participations")
    public ParticipationDto createParticipation(
        @PathVariable Long postId,  String userId) {

        return participationService.createParticipation(postId, userId);

    }


    // 참여자 조회시에 Status에 Join과 Joinready 둘다 조회가 필요할지? 어떤 상태의 유저가 필요한지 확인필요
    @GetMapping("{postId}/participations")
    public ResponseEntity<List<ParticipationDto>> getParticipations(
        @PathVariable Long postId) {
        return ResponseEntity.ok(
            participationService.getParticipationsStatusOfJoinAndJoinReady(postId));
    }

    // 참여취소는 정책적으로 디테일한 상의후 구현필요
    @GetMapping("{postId}/participations{participationId}")
    public ResponseEntity<Object> deleteParticipations() {
        return null;
    }


}
