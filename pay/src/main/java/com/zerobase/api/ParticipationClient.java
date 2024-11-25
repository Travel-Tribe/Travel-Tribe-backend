package com.zerobase.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "travel-service", url = "${travel-service.url}") // application.yml에 정의된 URL 사용
public interface ParticipationClient {

    @PutMapping("/internal/api/v1/posts/participations/{participationId}/success")
    ResponseEntity<Void> confirmParticipation(
        @PathVariable long participationId, @RequestHeader("X-User-Id") String userId);

    @PutMapping("/internal/api/v1/posts/participations/{participationId}/fail")
    ResponseEntity<Void> failedParticipation(
        @PathVariable long participationId, @RequestHeader("X-User-Id") String userId);


    @GetMapping("/internal/api/v1/posts/{postId}/participations/{participationId}/validate")
    ResponseEntity<Boolean> validateParticipationInfo(
        @PathVariable long postId, @PathVariable long participationId, @RequestHeader("X-User-Id") String userId);
}
