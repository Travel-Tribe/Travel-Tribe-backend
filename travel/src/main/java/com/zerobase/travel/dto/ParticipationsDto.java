package com.zerobase.travel.dto;

import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.type.DepositStatus;
import com.zerobase.travel.type.ParticipationStatus;
import com.zerobase.travel.type.RatingStatus;
import java.time.LocalDate;
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
public class ParticipationsDto {
    private Long participationId;
    private Long postId;
    private String userId;



    public static ParticipationsDto fromEntity(ParticipationEntity participationEntity) {
        return ParticipationsDto.builder()
            .participationId(participationEntity.getParticipationId())
            .postId(participationEntity.getPostEntity().getPostId())
            .userId(participationEntity.getUserId())
            .build();

    }

}