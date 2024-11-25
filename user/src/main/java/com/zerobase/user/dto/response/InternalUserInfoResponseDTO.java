package com.zerobase.user.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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
@JsonDeserialize(builder = InternalUserInfoResponseDTO.InternalUserInfoResponseDTOBuilder.class)
public class InternalUserInfoResponseDTO {

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

    public static InternalUserInfoResponseDTO fromDto(UserServiceDto byUserWithEmail) {
        return InternalUserInfoResponseDTO.builder()
            .id(byUserWithEmail.getId())
            .username(byUserWithEmail.getUsername())
            .nickname(byUserWithEmail.getNickname())
            .phone(byUserWithEmail.getPhone())
            .email(byUserWithEmail.getEmail())
            .status(byUserWithEmail.getStatus())
            .mbti(byUserWithEmail.getMbti())
            .smoking(byUserWithEmail.getSmoking())
            .introduction(byUserWithEmail.getIntroduction())
            .gender(byUserWithEmail.getGender())
            .birth(byUserWithEmail.getBirth())
            .ratingAvg(byUserWithEmail.getRatingAvg())
            .build();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class InternalUserInfoResponseDTOBuilder {

    }
}
