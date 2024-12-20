package com.zerobase.user.application;

import com.zerobase.user.service.UserParticipationService;
import com.zerobase.user.service.UserService;
import com.zerobase.user.service.dto.UserServiceDto;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserInfoFacade {

    private final UserParticipationService userParticipationService;
    private final UserService userService;
    private final RedisTemplate<String, Object> redisTemplate;

    public UserInfoFacadeDto getUserInfo(Long userId) {
        String cacheKey = "userInfo:" + userId;

        // Redis에서 캐시된 데이터 조회
        Object cachedData = redisTemplate.opsForValue().get(cacheKey);

        if (cachedData != null) {
            log.info("Cache hit for user ID: {}", userId);

            // 캐싱된 데이터가 UserInfoResponseDTO 타입으로 저장된 경우 처리
            if (cachedData instanceof UserInfoFacadeDto) {
                return (UserInfoFacadeDto) cachedData;
            }

            // 데이터 타입이 일치하지 않을 경우 예외 처리
            throw new IllegalStateException(
                "Unexpected cached data type: " + cachedData.getClass());
        }

        // Redis 캐시에 데이터가 없는 경우
        UserServiceDto userInfo = userService.getUserInfo(userId);

        Integer completedTripsCount = userParticipationService.getCompletedTripsCount(
            String.valueOf(userId));

        UserInfoFacadeDto build = UserInfoFacadeDto.builder()
            .id(userInfo.getId())
            .count(completedTripsCount)
            .username(userInfo.getUsername())
            .phone(userInfo.getPhone())
            .email(userInfo.getEmail())
            .mbti(userInfo.getMbti())
            .ratingAvg(userInfo.getRatingAvg())
            .birth(userInfo.getBirth())
            .introduction(userInfo.getIntroduction())
            .fileAddress(userInfo.getFileAddress())
            .nickname(userInfo.getNickname())
            .status(userInfo.getStatus())
            .smoking(userInfo.getSmoking())
            .gender(userInfo.getGender())
            .build();

        // Redis에 캐시 저장 (만료 시간 1시간 설정)
        redisTemplate.opsForValue().set(cacheKey, build, 1, TimeUnit.HOURS);
        log.info("Cache miss for user ID: {}. Data cached.", userId);

        return build;
    }
}
