package com.zerobase.travel.controller;

import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.type.ParticipationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ResponseParticipationsByUserDto {

    private Long participationId;
    private Long postId;
    private ParticipationStatus participationStatus;

    // 최대 참여수 2개이므로 n+1문제는 무시함
    public static ResponseParticipationsByUserDto fromEntity(ParticipationEntity participationEntity) {
        return ResponseParticipationsByUserDto.builder()
            .participationId(participationEntity.getParticipationId())
            .postId(participationEntity.getPostEntity().getPostId())
            .participationStatus(participationEntity.getParticipationStatus())
            .build();
    }
}
