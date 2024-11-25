package com.zerobase.travel.dto;

import com.zerobase.travel.entity.ParticipationEntity;
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
public class ParticipationDto {
    private Long participationId;
    private Long postId;
    private String userId;
    private String participationStatus;
    private String depositStatus;
    private String ratingStatus;
    private LocalDate depositReturnDate;



    public static ParticipationDto fromEntity(ParticipationEntity participationEntity) {
        return ParticipationDto.builder()
            .participationId(participationEntity.getParticipationId())
            .postId(participationEntity.getPostEntity().getPostId())
            .userId(participationEntity.getUserId())
            .participationStatus(participationEntity.getParticipationStatus().getKorean())
            .depositStatus(participationEntity.getDepositStatus().getKorean())
            .ratingStatus(participationEntity.getRatingStatus().getKorean())
            .depositReturnDate(participationEntity.getDepositReturnDate())
            .build();

    }

}