package com.zerobase.user.service.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.zerobase.user.dto.response.InternalUserInfoResponseDTO;
import com.zerobase.user.dto.response.UserInfoResponseDTO;
import com.zerobase.user.type.Gender;
import com.zerobase.user.type.MBTI;
import com.zerobase.user.type.Smoking;
import com.zerobase.user.type.UserStatus;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = UserServiceDto.UserServiceDtoBuilder.class)
public class UserServiceDto {
    private Long id;
    private String username;
    private String nickname;
    private String phone;
    private String email;
    private String fileAddress;
    private UserStatus status;
    private MBTI mbti;
    private Smoking smoking;
    private String introduction;
    private Gender gender;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birth;
    private Double ratingAvg;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserServiceDtoBuilder {

    }

}
