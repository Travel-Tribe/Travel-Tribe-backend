package com.zerobase.user.application;

import com.zerobase.user.service.UserParticipationService;
import com.zerobase.user.service.UserService;
import com.zerobase.user.service.dto.UserServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoFacade {

    private final UserParticipationService userParticipationService;
    private final UserService userService;

    public OtherUserInfoFacadeDto getOtherUserInfo(Long userId) {

        UserServiceDto userInfo = userService.getUserInfo(userId);

        Integer completedTripsCount = userParticipationService.getCompletedTripsCount(
            String.valueOf(userId));

        return OtherUserInfoFacadeDto.builder()
            .id(userInfo.getId())
            .count(completedTripsCount)
            .username(userInfo.getUsername())
            .phone(userInfo.getPhone())
            .email(userInfo.getEmail())
            .mbti(userInfo.getMbti())
            .ratingAvg(userInfo.getRatingAvg())
            .birth(userInfo.getBirth())
            .introduction(userInfo.getIntroduction())
            .nickname(userInfo.getNickname())
            .status(userInfo.getStatus())
            .smoking(userInfo.getSmoking())
            .gender(userInfo.getGender())
            .build();

    }


}
