package com.zerobase.travel.service;

import com.zerobase.travel.dto.response.VoteResponseDto;
import com.zerobase.travel.dto.response.VoteResponseDto.VotingStart;
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

    public VoteResponseDto.VotingStart getVotingStart(long userId, long postId) {

        validationGetVotingStart(userId, postId);

        return VotingStart.fromEntity(
            votingStartRepository.findByPostId(postId)
                .orElseThrow(() -> new RuntimeException())
        );
    }

    //TODO 김용민 validationRegisterRating 작성하기
    private void validationCreateVote(long organizerUserId, long postId) {

        //organizerUserId가 postId의 주최자 인지

        //이미 게시된 투표 인지

    }

    //TODO 김용민 validationRegisterRating 작성하기
    private void validationGetVotingStart(long userId, long postId) {
        // userId가 postId 여행에 참가한 사람 인지

    }
}
