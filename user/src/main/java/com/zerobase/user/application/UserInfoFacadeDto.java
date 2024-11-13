package com.zerobase.user.application;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.zerobase.user.type.Gender;
import com.zerobase.user.type.MBTI;
import com.zerobase.user.type.Smoking;
import com.zerobase.user.type.UserStatus;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = UserInfoFacadeDto.UserInfoFacadeDtoBuilder.class)
public class UserInfoFacadeDto {

    private Long id;
    private String username;
    private String nickname;
    private String phone;
    private String email;
    private UserStatus status;
    private MBTI mbti;
    private Smoking smoking;
    private String introduction;
    private Gender gender;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birth;
    private Double ratingAvg;
    private int count;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserInfoFacadeDtoBuilder {

    }

}
