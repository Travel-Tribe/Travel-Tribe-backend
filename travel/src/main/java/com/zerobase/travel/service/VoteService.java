package com.zerobase.travel.service;

import com.zerobase.travel.dto.response.VoteResponseDto;
import com.zerobase.travel.dto.response.VoteResponseDto.VotingStart;
import com.zerobase.travel.entity.VotingEntity;
import com.zerobase.travel.entity.VotingStartEntity;
import com.zerobase.travel.repository.VotingRepository;
import com.zerobase.travel.repository.VotingStartRepository;
import com.zerobase.travel.type.VotingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VotingStartRepository votingStartRepository;
    private final VotingRepository votingRepository;

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

    public void voteVoting(long userId, long postId, long votingStartsId, boolean approval) {

        validationVoteVoting(userId, postId, votingStartsId);

        VotingStartEntity votingStartEntity = votingStartRepository.findByPostId(postId)
            .orElseThrow(() -> new RuntimeException());


        votingRepository.save(VotingEntity.builder()
            .votingStartEntity(votingStartEntity)
            .approval(approval)
            .userId(userId)
            .build()
        );

        //post의 참여자와 vote한 참여자의 합이 같을 경우
        //1. voting start를 닫는다.
        //2. 투표 결과를 확인하고 post를 닫는 상태로 변경한다.

    }

    public VoteResponseDto.GetVote getVote(long userId, long postId, long votingStartsId) {
        validationGetVote(userId, postId, votingStartsId);

        VotingStartEntity votingStartEntity = votingStartRepository.findById(votingStartsId)
            .orElseThrow(() -> new RuntimeException());

        return VoteResponseDto.GetVote.fromEntity(
            votingRepository.findAllByVotingStartEntity(votingStartEntity)
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

    //TODO 김용민 validationRegisterRating 작성하기
    private void validationVoteVoting(long userId, long postId, long votingStartsId) {
        //votingStartsId가 postId의 투표인지
        
        //userId가 postId 여행에 참여하였는지
        
        //이미 투표를 하였는지
        
    }

    //TODO 김용민 validationRegisterRating 작성하기
    private void validationGetVote(long userId, long postId, long votingStartsId) {
        //votingStartsId가 postId의 투표인지

        //userId가 postId 여행에 참여하였는지
    }
}
