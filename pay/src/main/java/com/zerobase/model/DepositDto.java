package com.zerobase.model;

import com.zerobase.entity.DepositEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositDto {
    private Long depositId;
    private Long postId;
    private Long participationId;
    private String userId;

    public static DepositDto fromEntity(DepositEntity entity) {
        return DepositDto.builder()
            .depositId(entity.getDepositId())
            .postId(entity.getPostId())
            .participationId(entity.getParticipationId())
            .userId(entity.getUserId())
            .build();
    }
}
