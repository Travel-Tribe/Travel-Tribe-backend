package com.zerobase.travel.controller;

import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.service.ParticipationManagementService;
import com.zerobase.travel.service.ParticipationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "api/v1/posts/")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ParticipationController {

    private final ParticipationService participationService;
    private final ParticipationManagementService participationManagementService;

    // 참여
    @PostMapping("{postId}/participations")
    public ParticipationDto createParticipation(
        @PathVariable Long postId, @RequestHeader("X-User-Id") String userId) {
        log.info("createParticipation controller start");
        return participationService.createParticipation(postId, userId);

    }

    // 개인의 참여취소
    @DeleteMapping("{postId}/participations")
    public ResponseEntity<Object> deleteParticipations( @PathVariable Long postId,  @RequestHeader(value="userId") String userId ) {

        log.info("deleteParticipations controller start");
        participationManagementService.unjoinWithDepositTakenParticipation(postId,userId);

        return ResponseEntity.ok().build();
    }


    // 참여자 조회시에 Status에 Join 상태의 유저 리스트 확인
    @GetMapping("{postId}/participations")
    public ResponseEntity<List<ParticipationDto>> getParticipationsByPost(
        @PathVariable Long postId) {
        log.info("getParticipationsByPost controller start");
        return ResponseEntity.ok(
            participationService.getParticipationsDtosStatusOfJoin(postId));
    }

    // 유저들의 완료된 여행에 대해서 숫자 반환
    @GetMapping("/participations/by-userid/{userId}")
    public ResponseEntity<Integer> getParticipationsCompletedByUserId(
        @PathVariable String userId) {
        log.info("getParticipationsCompletedByUserId controller start");
        return ResponseEntity.ok(
            participationService.countParticipationsCompletedByUserId(userId));
    }




}
