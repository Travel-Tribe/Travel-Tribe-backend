package com.zerobase.travel.controller;

import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.dto.ResponseMyParticipationsDto;
import com.zerobase.travel.dto.ResponseParticipationsByPostDto;
import com.zerobase.travel.redis.PostLock;
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
    @PostLock
    @PostMapping("{postId}/participations")
    public ResponseEntity<ResponseMessage<ParticipationDto>> readyParticipation(
        @PathVariable long postId, @RequestHeader("X-User-Id") String userId,
        @RequestHeader("X-User-Email") String userEmail) {
        log.info("createParticipation controller start");
        return ResponseEntity.ok(ResponseMessage.success(
            participationManagementService.readyParticipation(postId, userId,
                userEmail)));
    }

    // 개인의 참여취소
    @DeleteMapping("{postId}/participations")
    public ResponseEntity<ResponseMessage<Object>> deleteParticipations( @PathVariable Long postId,  @RequestHeader("X-User-Id") String userId ) {

        log.info("deleteParticipations controller start");
        participationManagementService.unjoinParticipationWithDepositForfeited(
            postId, userId);

        return ResponseEntity.ok(ResponseMessage.success());
    }


    // 참여자 조회시에 Status에 Join/joinready 상태의 유저 리스트 확인
    @GetMapping("{postId}/participations")
    public ResponseEntity<ResponseMessage<List<ResponseParticipationsByPostDto>>> getParticipationsByPost(
        @PathVariable Long postId) {
        log.info("getParticipationsByPost controller start");
        return ResponseEntity.ok(
            ResponseMessage.success(
                participationService.getParticipationsDtosAfterJoin(postId)));
    }

    // 참여자 조회시에Join/joinready 상태의 자신의 참여 게시글 리스트 확인
    @GetMapping("/participations/by-join-joinready")
    public ResponseEntity<ResponseMessage<List<ResponseMyParticipationsDto>>> getMyParticipationsStatusofJoninAndJoinReady(
        @RequestHeader("X-User-Id") String userId) {
        log.info("getMyParticipationsStatusofJoninAndJoinReady controller start");
        return ResponseEntity.ok(
            ResponseMessage.success(
                participationService.getMyParticipationsStatusOfJoinAndJoinReady(userId)));
    }

    // 참여자 조회시에Join/joinready 상태의 자신의 참여 게시글 리스트 확인
    @GetMapping("/participations/by-travelfinished")
    public ResponseEntity<ResponseMessage<List<ResponseMyParticipationsDto>>> getMyParticipationsStatusofTravelFinished(
        @RequestHeader("X-User-Id") String userId) {
        log.info("getMyParticipationsStatusofTravelFinished controller start");
        return ResponseEntity.ok(
            ResponseMessage.success(
                participationService.getMyParticipationsStatusOfTravelFinished(userId)));
    }





}
