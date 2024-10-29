package com.zerobase.user.controller;

import com.zerobase.user.dto.request.JoinDTO;
import com.zerobase.user.dto.request.ProfileRequestDTO;
import com.zerobase.user.dto.response.ProfileResponseDTO;
import com.zerobase.user.dto.response.ResponseMessage;
import com.zerobase.user.service.JoinService;
import com.zerobase.user.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<?> joinProcess(@RequestBody @Valid JoinDTO joinDTO) {
        joinService.joinProcess(joinDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.success());
    }

    @PostMapping("/duplicate")
    public ResponseEntity<?> verifyDuplicateUser(
        @RequestParam("type") String type,
        @RequestParam("query") String query) {

        // joinService에서 중복 여부를 확인
        return ResponseEntity.ok(ResponseMessage.success(joinService.checkDuplicate(type, query)));
    }

    // 프로필 생성
    @PostMapping("/{userId}/profile")
    public ResponseEntity<?> createProfile(@PathVariable Long userId,
        @RequestBody ProfileRequestDTO profileRequest) {
        profileService.createProfile(userId, profileRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.success());
    }

    // 프로필 수정
    @PatchMapping("/{userId}/profile")
    public ResponseEntity<?> editProfile(@PathVariable Long userId,
        @RequestBody ProfileRequestDTO profileRequest) {
        profileService.editProfile(userId, profileRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success());
    }

    // 프로필 조회
    @GetMapping("/{userId}/profile")
    public ResponseEntity<?> getProfile(@PathVariable Long userId) {
        ProfileResponseDTO profileResponseDTO = profileService.getProfile(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success(profileResponseDTO));
    }

}

