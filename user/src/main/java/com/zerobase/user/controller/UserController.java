package com.zerobase.user.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.zerobase.user.application.UserInfoFacade;
import com.zerobase.user.application.UserInfoFacadeDto;
import com.zerobase.user.dto.request.EditUserInfoDTO;
import com.zerobase.user.dto.request.EditUserPasswordDTO;
import com.zerobase.user.dto.request.EmailVerificationDTO;
import com.zerobase.user.dto.request.JoinDTO;
import com.zerobase.user.dto.request.ProfileRequestDTO;
import com.zerobase.user.dto.request.ResetPasswordEmailDTO;
import com.zerobase.user.dto.request.UserEmailAuthenticationDTO;
import com.zerobase.user.dto.response.OtherUserInfoResponseDTO;
import com.zerobase.user.dto.response.ProfileResponseDTO;
import com.zerobase.user.dto.response.ResponseMessage;
import com.zerobase.user.dto.response.UserInfoResponseDTO;
import com.zerobase.user.entity.UserEntity;
import com.zerobase.user.service.ProfileService;
import com.zerobase.user.service.ReissueService;
import com.zerobase.user.service.UserService;
import com.zerobase.user.util.AuthenticationUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final ReissueService reissueService;
    private final UserInfoFacade userInfoFacade;
    private final AuthenticationUtil authenticationUtil;

    // 회원가입
    @PostMapping
    public ResponseEntity<ResponseMessage<Void>> joinProcess(@RequestBody @Valid JoinDTO joinDTO) {
        userService.joinProcess(joinDTO);
        return ResponseEntity.status(CREATED).body(ResponseMessage.success());
    }

    @PostMapping("/reissue")
    public ResponseEntity<ResponseMessage<Void>> reissue(HttpServletRequest request,
        HttpServletResponse response) {
        reissueService.reissue(request, response);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage.success());
    }

    // 회원 정보 변경
    @PatchMapping("/info")
    public ResponseEntity<ResponseMessage<Void>> editUserInfo(
        @RequestBody @Valid EditUserInfoDTO editUserInfoDTO) {
        String email = authenticationUtil.getLoginedUserEmail();
        UserEntity currentUser = userService.getCurrentUser(email);
        userService.editUserInfoProcess(editUserInfoDTO, currentUser);
        return ResponseEntity.status(OK).body(ResponseMessage.success());
    }

    // 회원 이메일 인증 요청
    @PostMapping("/change-email/request")
    public ResponseEntity<ResponseMessage<Void>> UserEmailAuthentication(
        @RequestBody @Valid UserEmailAuthenticationDTO userEmailAuthenticationDTO) {
        String email = authenticationUtil.getLoginedUserEmail();
        userService.getCurrentUser(email);
        userService.userEmailAuthenticationProcess(userEmailAuthenticationDTO);
        return ResponseEntity.status(OK).body(ResponseMessage.success());
    }

    // 회원 이메일 인증 코드 검증
    @PostMapping("/change-email/verify")
    @Transactional
    public ResponseEntity<ResponseMessage<Void>> verifyEmailCode(
        @RequestBody @Valid EmailVerificationDTO emailVerificationDTO) {
        String currentUserEmail = authenticationUtil.getLoginedUserEmail();
        userService.verifyEmailCode(
            emailVerificationDTO.getEmail(),
            emailVerificationDTO.getCode(),
            currentUserEmail);

        return ResponseEntity.status(OK).body(ResponseMessage.success());
    }

    // 회원 비밀번호 변경
    @PatchMapping("/password")
    public ResponseEntity<ResponseMessage<Void>> editUserPassword(
        @RequestBody @Valid EditUserPasswordDTO editUserPasswordDTO) {
        String email = authenticationUtil.getLoginedUserEmail();
        UserEntity currentUser = userService.getCurrentUser(email);
        userService.editUserPasswordProcess(editUserPasswordDTO, currentUser);
        return ResponseEntity.status(OK).body(ResponseMessage.success());
    }

    // 회원 비밀번호 초기화
    @PostMapping("/reset-password")
    public ResponseEntity<ResponseMessage<Void>> resetPassword(
        @RequestBody @Valid ResetPasswordEmailDTO resetPasswordDTO) {
        userService.resetPasswordProcess(resetPasswordDTO);
        return ResponseEntity.status(OK).body(ResponseMessage.success());
    }

    // 회원 탈퇴
    @DeleteMapping
    public ResponseEntity<ResponseMessage<Void>> deleteProcess() {
        String email = authenticationUtil.getLoginedUserEmail();
        UserEntity currentUser = userService.getCurrentUser(email);
        userService.deleteProcess(currentUser);
        return ResponseEntity.status(CREATED).body(ResponseMessage.success());
    }

    // 나의 회원정보 조회
    @GetMapping
    public ResponseEntity<ResponseMessage<UserInfoResponseDTO>> getUserInfo() {
        String email = authenticationUtil.getLoginedUserEmail();
        UserInfoFacadeDto userInfoFacadeDto = userInfoFacade.getUserInfo(
            userService.getCurrentUser(email).getId());
        return ResponseEntity.status(OK)
            .body(ResponseMessage.success(UserInfoResponseDTO.fromDto(userInfoFacadeDto)));
    }

    // 다른 회원 정보 조회
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseMessage<OtherUserInfoResponseDTO>> getOtherUserInfo(
        @PathVariable Long userId) {

        UserInfoFacadeDto otherUserInfo = userInfoFacade.getUserInfo(userId);
        return ResponseEntity.status(OK)
            .body(ResponseMessage.success(OtherUserInfoResponseDTO.fromDto(otherUserInfo)));
    }

    @GetMapping("/duplicate")
    public ResponseEntity<ResponseMessage<Boolean>> verifyDuplicateUser(
        @RequestParam("type") String type,
        @RequestParam("query") String query) {

        // userService에서 중복 여부를 확인
        return ResponseEntity.ok(ResponseMessage.success(userService.checkDuplicate(type, query)));
    }

    // 프로필 생성
    @PostMapping("/profile")
    public ResponseEntity<ResponseMessage<Void>> createProfile(
        @RequestBody @Valid ProfileRequestDTO profileRequest) {
        String email = authenticationUtil.getLoginedUserEmail();
        UserEntity currentUser = userService.getCurrentUser(email);
        profileService.createProfile(profileRequest, currentUser);
        return ResponseEntity.status(CREATED).body(ResponseMessage.success());
    }

    // 프로필 수정
    @PatchMapping("/profile")
    public ResponseEntity<ResponseMessage<Void>> editProfile(
        @RequestBody @Valid ProfileRequestDTO profileRequest) {
        String email = authenticationUtil.getLoginedUserEmail();
        profileService.editProfile(profileRequest, email);
        return ResponseEntity.status(OK).body(ResponseMessage.success());
    }

    // 프로필 조회
    @GetMapping("/{userId}/profile")
    public ResponseEntity<ResponseMessage<ProfileResponseDTO>> getProfile(
        @PathVariable Long userId) {
        ProfileResponseDTO profileResponseDTO = profileService.getProfile(userId);
        return ResponseEntity.status(OK)
            .body(ResponseMessage.success(profileResponseDTO));
    }
}

