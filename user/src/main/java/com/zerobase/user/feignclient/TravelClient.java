package com.zerobase.user.feignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// User 서비스의 기본 URL을 설정합니다. Eureka 등을 사용하지 않는 경우, 직접 URL을 지정합니다.
@FeignClient(name = "post-service", url = "${post-service.url}") // application.yml에 정의된 URL 사용
public interface TravelClient {

    @GetMapping("/api/v1/posts/participations/by-userid/{userId}")
    ResponseEntity<Integer> getParticipationsCompletedByUserId(@PathVariable("userId") String userId);

}
