package com.zerobase.user.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.zerobase.user.application.UserInfoFacadeDto;
import com.zerobase.user.type.MBTI;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
@AllArgsConstructor
@JsonDeserialize(builder = UserInfoResponseDTO.UserInfoResponseDTOBuilder.class)
public class UserInfoResponseDTO {

    private Long id;
    private String username;
    private String nickname;
    private String phone;
    private String email;
    private String status;
    private MBTI mbti;
    private String smoking;
    private String introduction;
    private String gender;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birth;
    private Double ratingAvg;

    public static UserInfoResponseDTO fromDto(UserInfoFacadeDto userInfoFacadeDto) {
        return UserInfoResponseDTO.builder()
            .id(userInfoFacadeDto.getId())
            .username(userInfoFacadeDto.getUsername())
            .nickname(userInfoFacadeDto.getNickname())
            .phone(userInfoFacadeDto.getPhone())
            .email(userInfoFacadeDto.getEmail())
            .mbti(userInfoFacadeDto.getMbti())
            .introduction(userInfoFacadeDto.getIntroduction())
            .birth(userInfoFacadeDto.getBirth())
            .ratingAvg(userInfoFacadeDto.getRatingAvg())
            .smoking(userInfoFacadeDto.getSmoking().getSmoke())
            .gender(userInfoFacadeDto.getGender().getGender())
            .status(userInfoFacadeDto.getStatus().getStatus())
            .build();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserInfoResponseDTOBuilder {

    }
}
