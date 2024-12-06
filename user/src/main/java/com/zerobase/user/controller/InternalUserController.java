package com.zerobase.user.controller;

import static org.springframework.http.HttpStatus.OK;

import com.zerobase.user.dto.request.UserProfileAvgRating;
import com.zerobase.user.dto.response.BasicErrorCode;
import com.zerobase.user.dto.response.InternalUserInfoResponseDTO;
import com.zerobase.user.dto.response.ResponseMessage;
import com.zerobase.user.dto.response.UserInfoResponseDTO;
import com.zerobase.user.exception.BizException;
import com.zerobase.user.repository.UserRepository;
import com.zerobase.user.service.ProfileService;
import com.zerobase.user.service.UserService;
import com.zerobase.user.service.dto.UserServiceDto;
import com.zerobase.user.type.MBTI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/api/v1/users")
@RequiredArgsConstructor
public class InternalUserController {

    private final UserService userService;
    private final ProfileService profileService;


    // 사용자 정보 조회
    @GetMapping
    public ResponseEntity<ResponseMessage<InternalUserInfoResponseDTO>> searchUserInfo(
        @RequestParam String type,
        @RequestParam String query
    ) {

        UserServiceDto userServiceDto;

        if ("userId".equals(type)) {
            userServiceDto = userService.getUserInfo(Long.parseLong(query));
        } else if ("email".equals(type)) {
            userServiceDto = userService.findByUserWithEmail(query);
        } else {
            throw new BizException(BasicErrorCode.ILLEGAL_ARGUMENT__ERROR);
        }

        return ResponseEntity.status(OK).body(ResponseMessage.success(
            InternalUserInfoResponseDTO.fromDto(userServiceDto)
        ));
    }

    // 사용자 이메일로 프로필 조회
    @GetMapping("/{userEmail}")
    public ResponseEntity<ResponseMessage<InternalUserInfoResponseDTO>> getUserEmail(
        @PathVariable String userEmail) {
        UserServiceDto byUserWithEmail = userService.findByUserWithEmail(userEmail);
        return ResponseEntity.status(OK).body(ResponseMessage.success(
            InternalUserInfoResponseDTO.fromDto(byUserWithEmail))
        );
    }

    // 사용자 아이디로 프로필 조회
    @GetMapping("/id/{userId}")
    public ResponseEntity<ResponseMessage<InternalUserInfoResponseDTO>> getUserInfoByUserId(
        @PathVariable long userId) {
        UserServiceDto byUserWithId = userService.findByUserWithId(userId);
        return ResponseEntity.status(OK).body(ResponseMessage.success(
            InternalUserInfoResponseDTO.fromDto(byUserWithId))
        );
    }

    // 사용자 평균평점을 업데이트 시켜주는 기능
    @PutMapping("/{userId}/profile-rating")
    public ResponseEntity<?> updateUserProfileAvgRating(@PathVariable Long userId, @RequestBody
    UserProfileAvgRating userProfileAvgRating) {
        Double avgRating = userProfileAvgRating.getAvgRating();
        userService.updateUserProfileAvgRating(userId, avgRating);
        return ResponseEntity.status(OK).body(ResponseMessage.success());
    }

    // 사용자 MBTI 보내주는 기능
    @GetMapping("/{userId}/mbti")
    public ResponseEntity<ResponseMessage<MBTI>> getUserProfile(@PathVariable Long userId) {
        return ResponseEntity.status(OK)
            .body(ResponseMessage.success(profileService.getProfile(userId).getMbti()));
    }

}
