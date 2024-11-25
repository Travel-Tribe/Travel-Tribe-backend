package com.zerobase.travel.application;

import com.zerobase.travel.service.RatingService;
import com.zerobase.travel.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VotingFacade {

    private final VoteService voteService;
    private final RatingService ratingService;

    public void voteVoting(long userId, long postId, long votingStartsId, boolean approval) {
        voteService.voteVoting(userId, postId, votingStartsId, approval);

        if (!voteService.isDoneVote(postId, votingStartsId)) {
            return;
        }



        // 평점 -0.5
        // 보증금 반환
        // 게시글 삭제
        // voting end

    }


}
