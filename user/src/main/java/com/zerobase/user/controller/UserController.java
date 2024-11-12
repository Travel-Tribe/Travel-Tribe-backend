package com.zerobase.user.controller;

import static com.zerobase.user.dto.response.BasicErrorCode.INVALID_AUTHENTICATION_CODE_ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.UNAUTHORIZED_ERROR;
import static com.zerobase.user.dto.response.ValidErrorCode.USER_NOT_FOUND_ERROR;
import static org.springframework.http.HttpStatus.*;

import com.zerobase.user.dto.request.EditUserInfoDTO;
import com.zerobase.user.dto.request.EditUserPasswordDTO;
import com.zerobase.user.dto.request.EmailVerificationDTO;
import com.zerobase.user.dto.request.JoinDTO;
import com.zerobase.user.dto.request.ProfileRequestDTO;
import com.zerobase.user.dto.request.ResetPasswordEmailDTO;
import com.zerobase.user.dto.request.UserEmailAuthenticationDTO;
import com.zerobase.user.dto.response.ProfileResponseDTO;
import com.zerobase.user.dto.response.ResponseMessage;
import com.zerobase.user.entity.UserEntity;
import com.zerobase.user.exception.BizException;
import com.zerobase.user.jwt.CustomUserDetails;
import com.zerobase.user.repository.UserRepository;
import com.zerobase.user.service.ReissueService;
import com.zerobase.user.service.UserService;
import com.zerobase.user.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
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

    private final UserService userService;
    private final ProfileService profileService;
    private final UserRepository userRepository;
    private final ReissueService reissueService;

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
        userService.joinProcess(joinDTO);
        return ResponseEntity.status(CREATED).body(ResponseMessage.success());
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        reissueService.reissue(request, response);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success());
    }

    // 회원 정보 변경
    @PatchMapping("/Info")
    public ResponseEntity<?> editUserInfo(@RequestBody @Valid EditUserInfoDTO editUserInfoDTO,
        Authentication authentication) {
        UserEntity currentUser = getCurrentUser(authentication);
        userService.editUserInfoProcess(editUserInfoDTO, currentUser);
        return ResponseEntity.status(OK).body(ResponseMessage.success());
    }

    // 회원 이메일 인증 요청
    @PostMapping("/change-email/request")
    public ResponseEntity<?> UserEmailAuthentication(
        @RequestBody @Valid UserEmailAuthenticationDTO userEmailAuthenticationDTO,
        Authentication authentication) {
        getCurrentUser(authentication);
        userService.userEmailAuthenticationProcess(userEmailAuthenticationDTO);
        return ResponseEntity.status(OK).body(ResponseMessage.success());
    }

    // 회원 이메일 인증 코드 검증
    @PostMapping("/change-email/verify")
    @Transactional
    public ResponseEntity<?> verifyEmailCode(@RequestBody @Valid EmailVerificationDTO emailVerificationDTO,  Authentication authentication) {
        UserEntity currentUser = getCurrentUser(authentication);
        boolean isVerified = userService.verifyEmailCode(emailVerificationDTO.getEmail(), emailVerificationDTO.getCode());

        if (isVerified) {
            currentUser.setEmail(emailVerificationDTO.getEmail());
            return ResponseEntity.ok(ResponseMessage.success());
        } else {
            return ResponseEntity.status(BAD_REQUEST)
                .body(ResponseMessage.fail(INVALID_AUTHENTICATION_CODE_ERROR));
        }
    }

    // 회원 비밀번호 변경
    @PatchMapping("/password")
    public ResponseEntity<?> editUserPassword(
        @RequestBody @Valid EditUserPasswordDTO editUserPasswordDTO,
        Authentication authentication) {
        UserEntity currentUser = getCurrentUser(authentication);
        userService.editUserPasswordProcess(editUserPasswordDTO, currentUser);
        return ResponseEntity.status(OK).body(ResponseMessage.success());
    }

    // 회원 비밀번호 초기화
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
        @RequestBody @Valid ResetPasswordEmailDTO resetPasswordDTO){
        userService.resetPasswordProcess(resetPasswordDTO);
        return ResponseEntity.status(OK).body(ResponseMessage.success());
    }

    // 회원 탈퇴
    @DeleteMapping
    public ResponseEntity<?> deleteProcess(Authentication authentication) {
        UserEntity currentUser = getCurrentUser(authentication);
        userService.deleteProcess(currentUser);
        return ResponseEntity.status(CREATED).body(ResponseMessage.success());
    }

    // 나의 회원정보 조회
    @GetMapping
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        return ResponseEntity.status(OK)
            .body(ResponseMessage.success(userService.getUserInfo(getCurrentUser(authentication))));
    }

    // 다른 회원 정보 조회
    @GetMapping("/{userId}")
    public ResponseEntity<?> getOtherUserInfo(@PathVariable Long userId) {
        return ResponseEntity.status(OK)
            .body(ResponseMessage.success(userService.getOtherUserInfo(userId)));
    }

    @PostMapping("/duplicate")
    public ResponseEntity<?> verifyDuplicateUser(
        @RequestParam("type") String type,
        @RequestParam("query") String query) {

        // userService에서 중복 여부를 확인
        return ResponseEntity.ok(ResponseMessage.success(userService.checkDuplicate(type, query)));
    }

    // 프로필 생성
    @PostMapping("/profile")
    public ResponseEntity<?> createProfile(
        @RequestBody @Valid ProfileRequestDTO profileRequest, Authentication authentication) {
        UserEntity currentUser = getCurrentUser(authentication);
        profileService.createProfile(profileRequest, currentUser);
        return ResponseEntity.status(CREATED).body(ResponseMessage.success());
    }

    // 프로필 수정
    @PatchMapping("/profile")
    public ResponseEntity<?> editProfile(
        @RequestBody @Valid ProfileRequestDTO profileRequest, Authentication authentication) {
        UserEntity currentUser = getCurrentUser(authentication);
        profileService.editProfile(profileRequest, currentUser);
        return ResponseEntity.status(OK).body(ResponseMessage.success());
    }

    // 프로필 조회
    @GetMapping("/{userId}/profile")
    public ResponseEntity<?> getProfile(@PathVariable Long userId) {
        ProfileResponseDTO profileResponseDTO = profileService.getProfile(userId);
        return ResponseEntity.status(OK)
            .body(ResponseMessage.success(profileResponseDTO));
    }

}

