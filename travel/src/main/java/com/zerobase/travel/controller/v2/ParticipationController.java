package com.zerobase.travel.controller.v2;

import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.dto.ReseponseParticipation;
import com.zerobase.travel.dto.ResponseMyParticipationsDto;
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

@RequestMapping(value = "api/v2/posts/")
@RestController("participationControllerV2")
@RequiredArgsConstructor
@Slf4j
public class ParticipationController {

    private final ParticipationService participationService;
    private final ParticipationManagementService participationManagementService;


    // 개인의 참여취소
    @DeleteMapping("{post_id}/participations/{participation_id}")
    public ResponseEntity<ResponseMessage<Object>> deleteParticipations( @PathVariable Long postId,  @RequestHeader("X-User-Id") String userId ) {

        log.info("deleteParticipations controller start");
        participationManagementService.unjoinParticipationWithDepositForfeited(
            postId, userId);

        return ResponseEntity.ok(ResponseMessage.success());
    }


    // 참여자 조회시에 Status에 Join/joinready 상태의 유저 리스트 확인
    @GetMapping("{postId}/participations?user_id={user_id}&status={join}&status={join_ready}")
    public ResponseEntity<ResponseMessage<List<ReseponseParticipation>>> getParticipationsByPost(
        @PathVariable Long postId) {
        log.info("getParticipationsByPost controller start");
        return ResponseEntity.ok(
            ResponseMessage.success(
                participationManagementService.getParticipationsByPostIdAndStatusActive(postId)));
    }

    // 참여자 조회시에Join/joinready 상태의 자신의 참여 게시글 리스트 확인
    @GetMapping("/participations?user_id={user_id}&status={travel_finished}")
    public ResponseEntity<ResponseMessage<List<ResponseMyParticipationsDto>>> getMyParticipationsStatusofJoninAndJoinReady(
        @RequestHeader("X-User-Id") String userId) {
        log.info("getMyParticipationsStatusofJoninAndJoinReady controller start");
        return ResponseEntity.ok(
            ResponseMessage.success(
                participationService.getMyParticipationsStatusOfJoinAndJoinReady(userId)));
    }





}
