package com.zerobase.travel.dto;

import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.type.DepositStatus;
import com.zerobase.travel.type.ParticipationStatus;
import com.zerobase.travel.type.RatingStatus;
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
    private ParticipationStatus participationStatus;
    private DepositStatus depositStatus;
    private RatingStatus ratingStatus;


    public static ParticipationDto fromEntity(ParticipationEntity participationEntity) {
        return ParticipationDto.builder()
            .participationId(participationEntity.getParticipationId())
            .postId(participationEntity.getPostEntity().getPostId())
            .userId(participationEntity.getUserId())
            .participationStatus(participationEntity.getParticipationStatus())
            .depositStatus(participationEntity.getDepositStatus())
            .ratingStatus(participationEntity.getRatingStatus())
            .build();

    }

}