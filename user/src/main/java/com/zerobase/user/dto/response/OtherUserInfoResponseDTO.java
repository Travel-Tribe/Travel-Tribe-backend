package com.zerobase.user.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.zerobase.user.type.Gender;
import com.zerobase.user.type.UserStatus;
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
    private String gender;
    private String status;

    @JsonPOJOBuilder(withPrefix = "")
    public static class OtherUserInfoResponseDTOBuilder {

    }
}
