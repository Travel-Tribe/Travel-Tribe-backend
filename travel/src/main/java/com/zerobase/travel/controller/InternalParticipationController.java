package com.zerobase.travel.controller;

import com.zerobase.travel.service.ParticipationManagementService;
import com.zerobase.travel.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/internal/api/v1/posts")
@RestController
@RequiredArgsConstructor
@Slf4j
public class InternalParticipationController {

    private final ParticipationService participationService;
    private final ParticipationManagementService participationManagementService;


    // 참여 확정 : 프론트에서 결제가 완료되면 호출하도록 이벤트 설정
    @PutMapping("/participations/{participationId}/success")
    public ResponseEntity<Void> confirmParticipation(
        @PathVariable long participationId, @RequestHeader("X-User-Id") String userId) {
        log.info("success participation controller start");
        participationManagementService.successPaymentParticipation(participationId, userId);
        return ResponseEntity.ok().build();
    }

    // 참여 확정 : 프론트에서 결제가 완료되면 호출하도록 이벤트 설정
    @PutMapping("/participations/{participationId}/fail")
    public ResponseEntity<Void> failParticipation(
        @PathVariable long participationId, @RequestHeader("X-User-Id") String userId) {
        log.info("failed participation controller start");
        participationManagementService.failedPaymentParticipation(participationId, userId);
        return ResponseEntity.ok().build();
    }


    // 유저들의 완료된 여행에 대해서 숫자 반환
    @GetMapping("/participations/by-userid/{userId}")
    public ResponseEntity<Integer> getParticipationsCompletedByUserId(
        @PathVariable String userId) {
        log.info("getParticipationsCompletedByUserId controller start");
        return ResponseEntity.ok(
            participationService.countParticipationsCompletedByUserId(userId));
    }


    // pay 모듈에 전달된 participation 정보 검증
    @GetMapping("{postId}/participations/{participationId}/validate")
    public ResponseEntity<Boolean> validateParticipationInfo(
        @PathVariable long postId, @PathVariable long participationId,
        @RequestHeader("X-User-Id") String userId ) {

        return ResponseEntity.ok(participationService
            .validateParticipationInfoUserIdAndPostId(postId, participationId, userId));
    }






}