package com.zerobase.travel.dto.response;

import com.zerobase.travel.entity.VotingStartEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class VoteResponseDto {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class VotingStart {

        private long votingStartsId;
        private String votingStatus;

        public static VotingStart fromEntity(VotingStartEntity entity) {
            return VotingStart.builder()
                .votingStartsId(entity.getId())
                .votingStatus(entity.getVotingStatus().toString())
                .build();
        }

    }

}
