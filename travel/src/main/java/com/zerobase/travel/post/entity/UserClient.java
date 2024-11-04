package com.zerobase.travel.post.entity;

import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// User 서비스의 기본 URL을 설정합니다. Eureka 등을 사용하지 않는 경우, 직접 URL을 지정합니다.
@FeignClient(name = "user-service", url = "${user-service.url}") // application.yml에 정의된 URL 사용
public interface UserClient {

    @GetMapping("/internal/api/v1/users/{userEmail}")
    UserInfoResponseDTO getUserInfoByEmail(@PathVariable("userEmail") String userEmail);
}
