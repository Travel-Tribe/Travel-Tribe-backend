package com.zerobase.travel.dto;

import com.zerobase.travel.entity.ParticipationEntity;
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
public class ParticipationDto {
    private Long participationId;
    private Long postId;
    private String userId;


    public static ParticipationDto fromEntity(ParticipationEntity participationEntity) {
        return ParticipationDto.builder()
            .participationId(participationEntity.getParticipationId())
            .postId(participationEntity.getPostEntity().getPostId())
            .userId(participationEntity.getUserId())
            .build();

    }

}
