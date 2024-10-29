package com.zerobase.travel.service;

import com.zerobase.travel.entity.VotingStartEntity;
import com.zerobase.travel.repository.VotingStartRepository;
import com.zerobase.travel.type.VotingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VotingStartRepository votingStartRepository;

    public void createVote(long userId, long postId) {

        validationCreateVote(userId, postId);

        votingStartRepository.save(
            VotingStartEntity.builder()
                .postId(postId)
                .votingStatus(VotingStatus.STARTING)
                .build()
        );

    }

    //TODO 김용민 validationRegisterRating 작성하기
    private void validationCreateVote(long organizerUserId, long postId) {

        //organizerUserId가 postId의 주최자 인지

        //이미 게시된 투표 인지

    }

}
