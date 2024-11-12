package com.zerobase.travel.controller;

import com.zerobase.travel.service.ParticipationManagementService;
import com.zerobase.travel.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/internal/api/v1/posts/participations")
@RestController
@RequiredArgsConstructor
@Slf4j
public class InternalParticipationController {

    private final ParticipationService participationService;
    private final ParticipationManagementService participationManagementService;


    // 참여 확정 : 프론트에서 결제가 완료되면 호출하도록 이벤트 설정
    @PutMapping("/{participationId}/success")
    public ResponseEntity<Void> confirmParticipation(
        @PathVariable long participationId, @RequestHeader("X-User-Id") String userId) {
        log.info("success participation controller start");
        participationManagementService.successPaymentParticipation(participationId, userId);
        return ResponseEntity.ok().build();
    }

    // 참여 확정 : 프론트에서 결제가 완료되면 호출하도록 이벤트 설정
    @PutMapping("/{participationId}/fail")
    public ResponseEntity<Void> failParticipation(
        @PathVariable long participationId, @RequestHeader("X-User-Id") String userId) {
        log.info("failed participation controller start");
        participationManagementService.failedPaymentParticipation(participationId, userId);
        return ResponseEntity.ok().build();
    }




}
