package com.zerobase.travel.dto.response;

import com.zerobase.travel.entity.VotingEntity;
import com.zerobase.travel.entity.VotingStartEntity;
import java.util.List;
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

    @Getter
    @Setter
    @Builder
    @ToString
    public static class GetVote {

        private List<Vote> votings;

        public static GetVote fromEntity(List<VotingEntity> entity) {

            return GetVote.builder()
                .votings(
                    entity.stream()
                        .map(Vote::fromEntity)
                        .toList()
                )
                .build();

        }


        @Getter
        @Setter
        @Builder
        @ToString
        public static class Vote {

            private long votingId;
            private long votingStartId;
            private long userId;
            private Boolean approval;

            public static Vote fromEntity(VotingEntity entity) {
                return Vote.builder()
                    .votingId(entity.getId())
                    .votingStartId(entity.getVotingStartEntity().getId())
                    .userId(entity.getUserId())
                    .approval(entity.getApproval())
                    .build();
            }

        }

    }

}
