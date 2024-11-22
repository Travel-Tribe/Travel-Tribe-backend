package com.zerobase.travel.controller;

import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.type.ParticipationStatus;
import com.zerobase.travel.type.RatingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseMyParticipationsDto {

    private Long participationId;
    private Long postId;
    private String participationStatus;
    private String ratingStatus;

    // 최대 참여수 2개이므로 n+1문제는 무시함
    public static ResponseMyParticipationsDto fromEntity(ParticipationEntity participationEntity) {
        return ResponseMyParticipationsDto.builder()
            .participationId(participationEntity.getParticipationId())
            .postId(participationEntity.getPostEntity().getPostId())
            .participationStatus(participationEntity.getParticipationStatus().getKorean())
            .ratingStatus(participationEntity.getRatingStatus().getKorean())
            .build();
    }
}
