package com.zerobase.user.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.zerobase.user.service.dto.UserServiceDto;
import com.zerobase.user.type.Gender;
import com.zerobase.user.type.MBTI;
import com.zerobase.user.type.Smoking;
import com.zerobase.user.type.UserStatus;
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

    public static UserInfoResponseDTO fromDto(UserServiceDto userServiceDto) {
        return UserInfoResponseDTO.builder()
            .id(userServiceDto.getId())
            .username(userServiceDto.getUsername())
            .nickname(userServiceDto.getNickname())
            .phone(userServiceDto.getPhone())
            .email(userServiceDto.getEmail())
            .mbti(userServiceDto.getMbti())
            .introduction(userServiceDto.getIntroduction())
            .birth(userServiceDto.getBirth())
            .ratingAvg(userServiceDto.getRatingAvg())
            .smoking(userServiceDto.getSmoking().getSmoke())
            .gender(userServiceDto.getGender().getGender())
            .status(userServiceDto.getStatus().getStatus())
            .build();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserInfoResponseDTOBuilder {

    }
}
