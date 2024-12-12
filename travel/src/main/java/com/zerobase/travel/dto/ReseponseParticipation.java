package com.zerobase.travel.dto;

import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import com.zerobase.travel.post.type.MBTI;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReseponseParticipation {
    private Long participationId;
    private Long postId;
    private String userId;
    private String nickname;
    private MBTI mbti;
    private String profileFile;


    public static ReseponseParticipation fromDtos( ParticipationsByPostDto participationsByPostDto ,UserInfoResponseDTO userInfoResponseDTO){
        return ReseponseParticipation.builder()
            .participationId(participationsByPostDto.getParticipationId())
            .postId(participationsByPostDto.getPostId())
            .userId(participationsByPostDto.getUserId())
            .nickname(userInfoResponseDTO.getNickname())
            .mbti(userInfoResponseDTO.getMbti())
            .profileFile(userInfoResponseDTO.getFileAddress())
            .build();

    }

}
