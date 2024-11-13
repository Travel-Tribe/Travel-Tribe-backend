package com.zerobase.user.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.zerobase.user.application.OtherUserInfoFacadeDto;
import com.zerobase.user.service.dto.UserServiceDto;
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

    public static OtherUserInfoResponseDTO fromDto(OtherUserInfoFacadeDto otherUserInfoFacadeDto) {
        return OtherUserInfoResponseDTO.builder()
            .username(otherUserInfoFacadeDto.getUsername())
            .nickname(otherUserInfoFacadeDto.getNickname())
            .email(otherUserInfoFacadeDto.getEmail())
            .count(otherUserInfoFacadeDto.getCount())
            .ratingAvg(otherUserInfoFacadeDto.getRatingAvg())
            .gender(otherUserInfoFacadeDto.getGender().getGender())
            .status(otherUserInfoFacadeDto.getStatus().getUserStatus())
            .build();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class OtherUserInfoResponseDTOBuilder {

    }
}
