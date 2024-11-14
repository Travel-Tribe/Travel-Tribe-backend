package com.zerobase.travel.dto;

import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.type.ParticipationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseParticipationsDto {
    private Long participationId;
    private Long postId;
    private String userId;



    public static ResponseParticipationsDto fromEntity(ParticipationEntity participationEntity) {
        return ResponseParticipationsDto.builder()
            .participationId(participationEntity.getParticipationId())
            .postId(participationEntity.getPostEntity().getPostId())
            .userId(participationEntity.getUserId())
            .build();

    }

}