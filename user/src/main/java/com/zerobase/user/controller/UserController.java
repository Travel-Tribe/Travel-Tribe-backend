package com.zerobase.user.controller;

import static com.zerobase.user.dto.response.BasicErrorCode.UNAUTHORIZED_ERROR;
import static com.zerobase.user.dto.response.UserErrorCode.USER_NOT_FOUND_ERROR;

import com.zerobase.user.dto.request.EditUserInfoDTO;
import com.zerobase.user.dto.request.JoinDTO;
import com.zerobase.user.dto.request.ProfileRequestDTO;
import com.zerobase.user.dto.request.EditUserPasswordDTO;
import com.zerobase.user.dto.response.ProfileResponseDTO;
import com.zerobase.user.dto.response.ResponseMessage;
import com.zerobase.user.entity.UserEntity;
import com.zerobase.user.exception.BizException;
import com.zerobase.user.jwt.CustomUserDetails;
import com.zerobase.user.repository.UserRepository;
import com.zerobase.user.service.JoinService;
import com.zerobase.user.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final JoinService joinService;
    private final ProfileService profileService;
    private final UserRepository userRepository;

    private UserEntity getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BizException(UNAUTHORIZED_ERROR);
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        return userRepository.findByEmail(customUserDetails.getUsername())
            .orElseThrow(() -> new BizException(USER_NOT_FOUND_ERROR));
    }

    // 회원가입
    @PostMapping
    public ResponseEntity<?> joinProcess(@RequestBody @Valid JoinDTO joinDTO) {
        joinService.joinProcess(joinDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.success());
    }

    // 회원 비밀번호 초기화 및 변경
    @PatchMapping("/Info")
    public ResponseEntity<?> editUserInfo(@RequestBody @Valid EditUserInfoDTO editUserInfoDTO, Authentication authentication) {
        UserEntity currentUser = getCurrentUser(authentication);
        joinService.editUserInfoProcess(editUserInfoDTO, currentUser);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success());
    }

    // 회원 비밀번호 초기화 및 변경
    @PatchMapping("/password")
    public ResponseEntity<?> editUserPassword(@RequestBody @Valid EditUserPasswordDTO editUserPasswordDTO, Authentication authentication) {
        UserEntity currentUser = getCurrentUser(authentication);
        joinService.editUserPasswordProcess(editUserPasswordDTO, currentUser);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success());
    }

    // 회원 탈퇴
    @DeleteMapping
    public ResponseEntity<?> deleteProcess(Authentication authentication) {
        UserEntity currentUser = getCurrentUser(authentication);
        joinService.deleteProcess(currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.success());
    }

    // 나의 회원정보 조회
    @GetMapping
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success(joinService.getUserInfo(getCurrentUser(authentication))));
    }

    // 다른 회원 정보 조회
    @GetMapping("/{userId}")
    public ResponseEntity<?> getOtherUserInfo(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success(joinService.getOtherUserInfo(userId)));
    }

    @PostMapping("/duplicate")
    public ResponseEntity<?> verifyDuplicateUser(
        @RequestParam("type") String type,
        @RequestParam("query") String query) {

        // joinService에서 중복 여부를 확인
        return ResponseEntity.ok(ResponseMessage.success(joinService.checkDuplicate(type, query)));
    }

    // 프로필 생성
    @PostMapping("/profile")
    public ResponseEntity<?> createProfile(
        @RequestBody @Valid ProfileRequestDTO profileRequest, Authentication authentication) {
        UserEntity currentUser = getCurrentUser(authentication);
        profileService.createProfile(profileRequest, currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.success());
    }

    // 프로필 수정
    @PatchMapping("/profile")
    public ResponseEntity<?> editProfile(
        @RequestBody @Valid ProfileRequestDTO profileRequest, Authentication authentication) {
        UserEntity currentUser = getCurrentUser(authentication);
        profileService.editProfile(profileRequest, currentUser);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success());
    }

    // 프로필 조회
    @GetMapping("/{userId}/profile")
    public ResponseEntity<?> getProfile(@PathVariable Long userId) {
        ProfileResponseDTO profileResponseDTO = profileService.getProfile(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success(profileResponseDTO));
    }

}

