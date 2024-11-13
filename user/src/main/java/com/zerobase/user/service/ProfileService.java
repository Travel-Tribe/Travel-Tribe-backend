package com.zerobase.user.service;

import static com.zerobase.user.dto.response.BasicErrorCode.ILLEGAL_ARGUMENT__ERROR;
import static com.zerobase.user.dto.response.ValidErrorCode.PROFILE_NOT_FOUND_ERROR;
import static com.zerobase.user.dto.response.ValidErrorCode.USER_NOT_FOUND_ERROR;

import com.zerobase.user.application.UserInfoFacade;
import com.zerobase.user.dto.request.ProfileRequestDTO;
import com.zerobase.user.dto.response.ProfileResponseDTO;
import com.zerobase.user.dto.response.UserInfoResponseDTO;
import com.zerobase.user.entity.LangAbilityEntity;
import com.zerobase.user.entity.ProfileEntity;
import com.zerobase.user.entity.UserEntity;
import com.zerobase.user.entity.VisitedCountryEntity;
import com.zerobase.user.exception.BizException;
import com.zerobase.user.kafka.UserMbtiChangedEvent;
import com.zerobase.user.repository.LangAbilityRepository;
import com.zerobase.user.repository.ProfileRepository;
import com.zerobase.user.repository.UserRepository;
import com.zerobase.user.repository.VisitedCountryRepository;
import com.zerobase.user.type.Gender;
import com.zerobase.user.type.MBTI;
import com.zerobase.user.type.Smoking;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final VisitedCountryRepository visitedCountryRepository;
    private final LangAbilityRepository langAbilityRepository;
    private final KafkaTemplate<String, UserMbtiChangedEvent> kafkaTemplate;
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${app.kafka.topics.user-mbti-changed}")
    private String userMbtiChangedTopic;

    @Transactional
    public void createProfile(ProfileRequestDTO request, UserEntity currentUser) {
        UserEntity user = userRepository.findById(currentUser.getId())
            .orElseThrow(() -> new BizException(USER_NOT_FOUND_ERROR));

        // MBTI, Smoking, Gender 변환 중 발생할 수 있는 예외를 처리
        MBTI mbti;
        Smoking smoking;
        Gender gender;

        try {
            mbti = MBTI.valueOf(request.getMbti().toUpperCase());
            smoking = Smoking.valueOf(request.getSmoking().toUpperCase());
            gender = Gender.valueOf(request.getGender().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BizException(ILLEGAL_ARGUMENT__ERROR);
        }

        // 프로필 생성
        ProfileEntity profile = ProfileEntity.builder()
            .user(user)
            .introduction(request.getIntroduction())
            .mbti(mbti)
            .smoking(smoking)
            .gender(gender)
            .birth(request.getBirth())
            .fileAddress(request.getFileAddress())
            .build();

        profileRepository.save(profile);

        // 방문 국가 저장
        List<VisitedCountryEntity> countries = request.getVisitedCountries().stream()
            .map(country -> new VisitedCountryEntity(profile, country))
            .collect(Collectors.toList());
        visitedCountryRepository.saveAll(countries);

        // 언어 능력 저장
        List<LangAbilityEntity> languages = request.getLangAbilities().stream()
            .map(lang -> new LangAbilityEntity(profile, lang))
            .collect(Collectors.toList());
        langAbilityRepository.saveAll(languages);
    }

    @Transactional
    public void editProfile(ProfileRequestDTO profileRequest, UserEntity currentUser) {
        // 사용자 및 프로필 조회
        userRepository.findById(currentUser.getId())
            .orElseThrow(() -> new BizException(USER_NOT_FOUND_ERROR));
        ProfileEntity profile = profileRepository.findByUserId(currentUser.getId())
            .orElseThrow(() -> new BizException(PROFILE_NOT_FOUND_ERROR));

        String userKey = "userInfo:" + currentUser.getId();
        String profileKey = "userProfile:" + currentUser.getId();

        // 기존 캐시 삭제
        redisTemplate.delete(userKey);
        redisTemplate.delete(profileKey);
        log.info("Cache deleted for user ID: {}", currentUser.getId());

        String mbtiString = profileRequest.getMbti().toUpperCase();

        // 프로필 정보 업데이트
        if (profileRequest.getIntroduction() != null) {
            profile.setIntroduction(profileRequest.getIntroduction());
        }
        if (profileRequest.getMbti() != null) {
            profile.setMbti(MBTI.valueOf(mbtiString));
        }
        if (profileRequest.getSmoking() != null) {
            profile.setSmoking(Smoking.valueOf(profileRequest.getSmoking().toUpperCase()));
        }
        if (profileRequest.getGender() != null) {
            profile.setGender(Gender.valueOf(profileRequest.getGender().toUpperCase()));
        }
        if (profileRequest.getBirth() != null) {
            profile.setBirth(profileRequest.getBirth());
        }
        if (profileRequest.getFileAddress() != null) {
            profile.setFileAddress(profileRequest.getFileAddress());
        }

        // 기존 방문 국가와 언어 삭제 및 업데이트
        if (profileRequest.getVisitedCountries() != null) {
            visitedCountryRepository.deleteAllByProfileEntity(profile);
            List<VisitedCountryEntity> newCountries = profileRequest.getVisitedCountries().stream()
                .map(country -> new VisitedCountryEntity(profile, country))
                .collect(Collectors.toList());
            visitedCountryRepository.saveAll(newCountries);
        }

        if (profileRequest.getLangAbilities() != null) {
            langAbilityRepository.deleteAllByProfileEntity(profile);
            List<LangAbilityEntity> newLanguages = profileRequest.getLangAbilities().stream()
                .map(lang -> new LangAbilityEntity(profile, lang))
                .collect(Collectors.toList());
            langAbilityRepository.saveAll(newLanguages);
        }

        // 변경된 프로필 정보를 DB에 반영 (더티 체킹에 의해 자동 반영)

        // 변경된 UserInfoResponseDTO 생성
        ProfileEntity profileEntity = profileRepository.findByUserId(currentUser.getId())
            .orElseThrow(() -> new BizException(PROFILE_NOT_FOUND_ERROR));

        // 이벤트 발행
        UserMbtiChangedEvent event = new UserMbtiChangedEvent(currentUser.getId(), mbtiString);
        kafkaTemplate.send(userMbtiChangedTopic, event);
    }


    public ProfileResponseDTO getProfile(Long userId) {
        // 사용자 및 프로필 조회
        userRepository.findById(userId)
            .orElseThrow(() -> new BizException(USER_NOT_FOUND_ERROR));
        ProfileEntity profile = profileRepository.findByUserId(userId)
            .orElseThrow(() -> new BizException(PROFILE_NOT_FOUND_ERROR));

        String cacheKey = "userProfile:" + userId;

        // Redis에서 캐시된 데이터 조회
        Object cachedData = redisTemplate.opsForValue().get(cacheKey);
        if (cachedData != null) {
            log.info("Cache hit for user ID: {}", userId);
            return (ProfileResponseDTO) cachedData;
        }

        ProfileResponseDTO userProfile = ProfileResponseDTO.fromEntity(profile);
        // Redis에 캐시 저장 (만료 시간 1시간 설정)
        redisTemplate.opsForValue().set(cacheKey, userProfile, 1, TimeUnit.HOURS);
        log.info("Cache miss for user ID: {}. Data cached.", userId);

        return userProfile;
    }
}
