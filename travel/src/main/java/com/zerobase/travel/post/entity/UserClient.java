package com.zerobase.travel.post.entity;

import com.zerobase.travel.api.UserApiRequestDto.UpdateUserRating;
import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import com.zerobase.travel.post.type.MBTI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

// User 서비스의 기본 URL을 설정합니다. Eureka 등을 사용하지 않는 경우, 직접 URL을 지정합니다.
@FeignClient(name = "user-service", url = "${user-service.url}") // application.yml에 정의된 URL 사용
public interface UserClient {

    @GetMapping("/internal/api/v1/users/{userEmail}")
    ResponseMessage<UserInfoResponseDTO> getUserInfoByEmail(@PathVariable("userEmail") String userEmail);

    @GetMapping("/internal/api/v1/users")
    ResponseMessage<UserInfoResponseDTO> searchUserInfo(
        @RequestParam("type") String type,
        @RequestParam("query") String query
    );

    @PutMapping("/internal/api/v1/users/{userId}/profile-rating")
    ResponseMessage<Void> updateUserRating(
        @PathVariable("userId") long userId,
        @RequestBody UpdateUserRating request
    );

    // 프로필 조회
    @GetMapping("/internal/api/v1/users/{userId}/mbti")
    ResponseMessage<MBTI> getUserMbti(@PathVariable Long userId);

    @GetMapping("/internal/api/v1/users/{userEmail}")
    ResponseEntity<ResponseMessage<UserInfoResponseDTO>> getUserInfoByUserEmail(
        @PathVariable String userEmail);
}
