package com.zerobase.user.service;

import static com.zerobase.user.dto.response.BasicErrorCode.AUTHENTICATION_CODE_EXPIRED_ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.NOT_FOUND_EMAIL_AUTHENTICATION_ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.UNAUTHORIZED_ERROR;
import static com.zerobase.user.dto.response.ValidErrorCode.PROFILE_NOT_FOUND_ERROR;
import static com.zerobase.user.dto.response.ValidErrorCode.USER_NOT_FOUND_ERROR;
import static com.zerobase.user.dto.response.ValidErrorCode.USER_PW_MISMATCH_ERROR;
import static com.zerobase.user.type.UserStatus.DEACTIVATED;

import com.zerobase.user.dto.request.EditUserInfoDTO;
import com.zerobase.user.dto.request.EditUserPasswordDTO;
import com.zerobase.user.dto.request.JoinDTO;
import com.zerobase.user.dto.request.ResetPasswordEmailDTO;
import com.zerobase.user.dto.request.UserEmailAuthenticationDTO;
import com.zerobase.user.dto.response.UserInfoResponseDTO;
import com.zerobase.user.entity.EmailVerificationEntity;
import com.zerobase.user.entity.ProfileEntity;
import com.zerobase.user.entity.UserEntity;
import com.zerobase.user.exception.BizException;
import com.zerobase.user.jwt.CustomUserDetails;
import com.zerobase.user.repository.EmailVerificationRepository;
import com.zerobase.user.repository.ProfileRepository;
import com.zerobase.user.repository.UserRepository;
import com.zerobase.user.service.dto.UserServiceDto;
import com.zerobase.user.type.Role;
import com.zerobase.user.type.UserStatus;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
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
    private final RedisTemplate<String, Object> redisTemplate;

    public UserEntity getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BizException(UNAUTHORIZED_ERROR);
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        return userRepository.findByEmail(customUserDetails.getUsername())
            .orElseThrow(() -> new BizException(USER_NOT_FOUND_ERROR));
    }

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

        String userKey = "userInfo:" + currentUser.getId();
        String profileKey = "userProfile:" + currentUser.getId();

        if (editUserInfoDTO.getNickname() != null) {
            currentUser.setNickname(editUserInfoDTO.getNickname());
        }

        if (editUserInfoDTO.getPhone() != null) {
            currentUser.setPhone(editUserInfoDTO.getPhone());
        }
        // 기존 캐시 삭제
        redisTemplate.delete(userKey);
        redisTemplate.delete(profileKey);

        log.info("Cache deleted for user ID: {}", currentUser.getId());
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

    public UserServiceDto getUserInfo(long userId) {
        UserEntity userEntity = userRepository.findById(userId)
            .orElseThrow(() -> new BizException(USER_NOT_FOUND_ERROR));
        ProfileEntity profileEntity = profileRepository.findByUserId(userEntity.getId())
            .orElseThrow(() -> new BizException(PROFILE_NOT_FOUND_ERROR));

        // 데이터베이스에서 조회
        UserServiceDto userInfo = UserServiceDto.builder()
            .id(userEntity.getId())
            .username(userEntity.getUsername())
            .nickname(userEntity.getNickname())
            .phone(userEntity.getPhone())
            .email(userEntity.getEmail())
            .status(userEntity.getStatus())
            .introduction(profileEntity.getIntroduction())
            .mbti(profileEntity.getMbti())
            .gender(profileEntity.getGender())
            .smoking(profileEntity.getSmoking())
            .birth(profileEntity.getBirth())
            .fileAddress(profileEntity.getFileAddress())
            .ratingAvg(profileEntity.getRatingAvg())
            .build();

        return userInfo;
    }

    @Transactional
    public void deleteProcess(UserEntity currentUser) {
        UserEntity userEntity = userRepository.findByEmail(
            currentUser.getEmail()).orElseThrow(() -> new BizException(USER_NOT_FOUND_ERROR));

        userEntity.setStatus(DEACTIVATED);

        String cacheKey = "userInfo:" + currentUser.getId();
        // 기존 캐시 삭제
        redisTemplate.delete(cacheKey);
        log.info("Cache deleted for user ID: {}", currentUser.getId());
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

    public UserServiceDto findByUserWithEmail(String userEmail) {
        UserEntity userEntity = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new BizException(USER_NOT_FOUND_ERROR));

        ProfileEntity profileEntity = profileRepository.findByUserId(userEntity.getId())
            .orElseThrow(() -> new BizException(PROFILE_NOT_FOUND_ERROR));

        UserServiceDto userInfo = UserServiceDto.builder()
            .id(userEntity.getId())
            .username(userEntity.getUsername())
            .nickname(userEntity.getNickname())
            .email(userEntity.getEmail())
            .status(userEntity.getStatus())
            .phone(userEntity.getPhone())
            .introduction(profileEntity.getIntroduction())
            .smoking(profileEntity.getSmoking())
            .gender(profileEntity.getGender())
            .mbti(profileEntity.getMbti())
            .birth(profileEntity.getBirth())
            .ratingAvg(profileEntity.getRatingAvg())
            .build();

        return userInfo;
    }

    @Transactional
    public void updateUserProfileAvgRating(Long userId, Double avg) {

        UserEntity userEntity = userRepository.findById(userId)
            .orElseThrow(() -> new BizException(USER_NOT_FOUND_ERROR));

        ProfileEntity profileEntity = profileRepository.findByUserId(userId)
            .orElseThrow(() -> new BizException(PROFILE_NOT_FOUND_ERROR));

        profileEntity.setRatingAvg(avg);

        String cacheKey = "userInfo:" + userId;
        // 기존 캐시 삭제
        redisTemplate.delete(cacheKey);
        log.info("Cache deleted for user ID: {}", userId);

        // 변경된 UserInfoResponseDTO 생성
        UserInfoResponseDTO updatedUserInfo = UserInfoResponseDTO.builder()
            .id(userEntity.getId())
            .username(userEntity.getUsername())
            .nickname(userEntity.getNickname())
            .phone(userEntity.getPhone())
            .email(userEntity.getEmail())
            .status(userEntity.getStatus().getUserStatus())
            // 프로필 관련 정보도 업데이트하는 경우 ProfileEntity를 다시 조회하여 설정
            .introduction(profileEntity.getIntroduction())
            .mbti(profileEntity.getMbti())
            .gender(profileEntity.getGender().getGender())
            .smoking(profileEntity.getSmoking().getSmoke())
            .birth(profileEntity.getBirth())
            .ratingAvg(profileEntity.getRatingAvg())
            .build();

        // Redis 캐시에 갱신된 데이터 저장 (만료 시간 1시간 설정)
        redisTemplate.opsForValue().set(cacheKey, updatedUserInfo, 1, TimeUnit.HOURS);
        log.info("Cache updated for user ID: {}", userId);
    }
}