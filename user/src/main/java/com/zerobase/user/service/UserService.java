package com.zerobase.user.service;

import static com.zerobase.user.dto.response.BasicErrorCode.AUTHENTICATION_CODE_EXPIRED_ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.NOT_FOUND_EMAIL_AUTHENTICATION_ERROR;
import static com.zerobase.user.dto.response.ValidErrorCode.USER_NOT_FOUND_ERROR;
import static com.zerobase.user.dto.response.ValidErrorCode.USER_PW_MISMATCH_ERROR;

import com.zerobase.user.dto.request.EditUserInfoDTO;
import com.zerobase.user.dto.request.EditUserPasswordDTO;
import com.zerobase.user.dto.request.JoinDTO;
import com.zerobase.user.dto.request.ResetPasswordEmailDTO;
import com.zerobase.user.dto.request.UserEmailAuthenticationDTO;
import com.zerobase.user.dto.response.OtherUserInfoResponseDTO;
import com.zerobase.user.dto.response.UserInfoResponseDTO;
import com.zerobase.user.entity.EmailVerificationEntity;
import com.zerobase.user.entity.ProfileEntity;
import com.zerobase.user.entity.UserEntity;
import com.zerobase.user.exception.BizException;
import com.zerobase.user.repository.EmailVerificationRepository;
import com.zerobase.user.repository.ProfileRepository;
import com.zerobase.user.repository.UserRepository;
import com.zerobase.user.type.Role;
import com.zerobase.user.type.UserStatus;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailVerificationRepository verificationRepository;
    private final EmailService emailService;
    private final ProfileRepository profileRepository;
    private final PasswordResetService passwordResetService;

    public void joinProcess(JoinDTO joinDTO) {
        log.info("Processing registration for email: {}", joinDTO.getEmail());

        // 이메일 중복 체크
        if (userRepository.existsByEmail(joinDTO.getEmail())) {
            log.warn("Email already exists: {}", joinDTO.getEmail());
            throw new DataIntegrityViolationException("이미 존재하는 이메일입니다.");
        }

        // 사용자 엔티티 생성 및 저장
        userRepository.save(buildUser(joinDTO));
        log.info("User registration successful for email: {}", joinDTO.getEmail());
    }

    @Transactional
    public void editUserPasswordProcess(EditUserPasswordDTO editUserDTO, UserEntity currentUser) {
        // 현재 비밀번호가 입력된 경우 확인
        if (editUserDTO.getPassword() != null) {
            if (!passwordEncoder.matches(editUserDTO.getPassword(), currentUser.getPassword())) {
                throw new BizException(USER_PW_MISMATCH_ERROR);
            }
        }
        // 새 비밀번호가 입력된 경우 암호화하여 저장
        if (editUserDTO.getNewPassword() != null) {
            currentUser.setPassword(passwordEncoder.encode(editUserDTO.getNewPassword()));
        }
    }

    @Transactional
    public void editUserInfoProcess(EditUserInfoDTO editUserInfoDTO, UserEntity currentUser) {
        currentUser.setNickname(editUserInfoDTO.getNickname());
        currentUser.setPhone(editUserInfoDTO.getPhone());
    }


    // UserEntity 빌더를 통한 엔티티 생성
    private UserEntity buildUser(JoinDTO joinDTO) {
        log.debug("Building user entity for email: {}", joinDTO.getEmail());
        return UserEntity.builder()
            .username(joinDTO.getUsername())
            .password(passwordEncoder.encode(joinDTO.getPassword()))
            .email(joinDTO.getEmail())
            .role(Role.ROLE_USER)
            .phone(joinDTO.getPhone())
            .nickname(joinDTO.getNickname())
            .status(UserStatus.ACTIVE)
            .build();
    }

    public boolean checkDuplicate(String type, String query) {
        return switch (type.toLowerCase()) {
            case "email" -> userRepository.existsByEmail(query);
            case "nickname" -> userRepository.existsByNickname(query);
            case "phone" -> userRepository.existsByPhone(query);
            default -> throw new IllegalArgumentException("Invalid type: " + type);
        };
    }

    public UserInfoResponseDTO getUserInfo(UserEntity currentUser) {
        return UserInfoResponseDTO.builder()
            .id(currentUser.getId())
            .username(currentUser.getUsername())
            .nickname(currentUser.getNickname())
            .phone(currentUser.getPhone())
            .email(currentUser.getEmail())
            .status(currentUser.getStatus())
            .build();
    }

    public OtherUserInfoResponseDTO getOtherUserInfo(Long userId) {

        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        if (!optionalUserEntity.isPresent()) {
            throw new BizException(USER_NOT_FOUND_ERROR);
        } else {
            UserEntity userEntity = optionalUserEntity.get();
            Optional<ProfileEntity> optionalProfileEntity = profileRepository.findByUserId(userEntity.getId());
            ProfileEntity profileEntity = optionalProfileEntity.get();
            return OtherUserInfoResponseDTO.builder()
                .username(userEntity.getUsername())
                .nickname(userEntity.getNickname())
                .email(userEntity.getEmail())
                .ratingAvg(profileEntity.getRatingAvg())
                .gender(profileEntity.getGender())
                .status(userEntity.getStatus())
                .build();
        }
    }

    public void deleteProcess(UserEntity currentUser) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(
            currentUser.getEmail());
        if (!optionalUserEntity.isPresent()) {
            throw new BizException(USER_NOT_FOUND_ERROR);
        } else {
            userRepository.delete(optionalUserEntity.get());
        }
    }

    @Transactional
    public boolean verifyEmailCode(String email, String code) {
        EmailVerificationEntity verification = verificationRepository.findByEmail(email)
            .orElseThrow(() -> new BizException(NOT_FOUND_EMAIL_AUTHENTICATION_ERROR));

        if (verification.getExpirationTime().isBefore(LocalDateTime.now())) {
            throw new BizException(AUTHENTICATION_CODE_EXPIRED_ERROR);
        }

        return verification.getVerificationCode().equals(code);
    }


    public void userEmailAuthenticationProcess(
        UserEmailAuthenticationDTO userEmailAuthenticationDTO) {
        String email = userEmailAuthenticationDTO.getEmail();

        // 인증 코드 생성 및 저장
        String verificationCode = generateVerificationCode();
        EmailVerificationEntity verification = new EmailVerificationEntity();
        verification.setEmail(email);
        verification.setVerificationCode(verificationCode);
        verification.setExpirationTime(LocalDateTime.now().plusMinutes(10));  // 10분 후 만료

        verificationRepository.save(verification);

        // 인증 코드 발송
        emailService.sendVerificationCode(email, verificationCode);
    }

    private String generateVerificationCode() {
        return String.valueOf((int) ((Math.random() * 900000) + 100000));  // 6자리 난수 생성
    }

    @Transactional
    public void resetPasswordProcess(ResetPasswordEmailDTO resetPasswordDTO) {
        String email = resetPasswordDTO.getEmail();
        String generateRandomPassword;
        String encryptedPassword;
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(email);
        if (optionalUserEntity.isEmpty()) {
            throw new BizException(USER_NOT_FOUND_ERROR);
        } else {
            UserEntity userEntity = optionalUserEntity.get();
            generateRandomPassword = passwordResetService.generateRandomPassword();
            encryptedPassword = passwordResetService.encryptPassword(generateRandomPassword);
            userEntity.setPassword(encryptedPassword);
        }
        emailService.sendResetPassword(email, generateRandomPassword);
    }

    public UserInfoResponseDTO findByUserWithEmail(String userEmail) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(userEmail);

        if (!optionalUserEntity.isPresent()) {
            throw new BizException(USER_NOT_FOUND_ERROR);
        }

        UserEntity userEntity = optionalUserEntity.get();

        return UserInfoResponseDTO.builder()
            .id(userEntity.getId())
            .username(userEntity.getUsername())
            .nickname(userEntity.getNickname())
            .email(userEntity.getEmail())
            .status(userEntity.getStatus())
            .phone(userEntity.getPhone())
            .build();
    }

    @Transactional
    public void updateUserProfileAvgRating(Long userId, Double avg) {
        Optional<ProfileEntity> optionalProfileEntity = profileRepository.findByUserId(userId);
        ProfileEntity profileEntity = optionalProfileEntity.get();
        profileEntity.setRatingAvg(avg);
    }
}