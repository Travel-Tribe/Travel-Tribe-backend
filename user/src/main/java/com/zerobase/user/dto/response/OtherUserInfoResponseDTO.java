package com.zerobase.user.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.zerobase.user.application.UserInfoFacadeDto;
import com.zerobase.user.type.Gender;
import com.zerobase.user.type.MBTI;
import com.zerobase.user.type.Smoking;
import com.zerobase.user.type.UserStatus;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
@AllArgsConstructor
@JsonDeserialize(builder = OtherUserInfoResponseDTO.OtherUserInfoResponseDTOBuilder.class)
public class OtherUserInfoResponseDTO {

    private String username;
    private String nickname;
    private String email;
    private Integer count; // 여행 횟수
    private Double ratingAvg;
    private String fileAddress;
    private String gender;
    private String status;
    private String mbti;

    private String phone;
    private String smoking;
    private String introduction;
    private LocalDate birth;

    public static OtherUserInfoResponseDTO fromDto(UserInfoFacadeDto userInfoFacadeDto) {
        return OtherUserInfoResponseDTO.builder()
            .username(userInfoFacadeDto.getUsername())
            .nickname(userInfoFacadeDto.getNickname())
            .email(userInfoFacadeDto.getEmail())
            .count(userInfoFacadeDto.getCount())
            .fileAddress(userInfoFacadeDto.getFileAddress())
            .mbti(userInfoFacadeDto.getMbti().name())
            .ratingAvg(userInfoFacadeDto.getRatingAvg())
            .gender(userInfoFacadeDto.getGender().getGender())
            .status(userInfoFacadeDto.getStatus().getUserStatus())
            .phone(userInfoFacadeDto.getPhone())
            .smoking(userInfoFacadeDto.getSmoking().getSmoke())
            .introduction(userInfoFacadeDto.getIntroduction())
            .birth(userInfoFacadeDto.getBirth())
            .build();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class OtherUserInfoResponseDTOBuilder {

    }
}
