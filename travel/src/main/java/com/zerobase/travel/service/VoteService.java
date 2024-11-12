package com.zerobase.travel.service;

import com.zerobase.travel.dto.response.VoteResponseDto;
import com.zerobase.travel.dto.response.VoteResponseDto.VotingStart;
import com.zerobase.travel.entity.VotingEntity;
import com.zerobase.travel.entity.VotingStartEntity;
import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.errorcode.VoteErrorCode;
import com.zerobase.travel.post.repository.PostRepository;
import com.zerobase.travel.repository.ParticipationRepository;
import com.zerobase.travel.repository.VotingRepository;
import com.zerobase.travel.repository.VotingStartRepository;
import com.zerobase.travel.type.VotingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final PostRepository postRepository;
    private final VotingStartRepository votingStartRepository;
    private final VotingRepository votingRepository;
    private final ParticipationRepository participationRepository;

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
                .orElseThrow(() -> new BizException(VoteErrorCode.NOT_READY_VOTING_START))
        );
    }

    @Transactional
    public void voteVoting(long userId, long postId, long votingStartsId, boolean approval) {

        VotingStartEntity votingStartEntity = votingStartRepository.findByPostId(postId)
            .orElseThrow(() -> new BizException(VoteErrorCode.NOT_READY_VOTING_START));

        validationVoteVoting(userId, postId, votingStartsId, votingStartEntity);

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
            .orElseThrow(() -> new BizException(VoteErrorCode.NOT_READY_VOTING_START));

        return VoteResponseDto.GetVote.fromEntity(
            votingRepository.findAllByVotingStartEntity(votingStartEntity)
        );
    }

    private void validationCreateVote(long organizerUserId, long postId) {

        //organizerUserId가 postId의 주최자 인지
        if (!postRepository.existsByPostIdAndUserId(postId, organizerUserId)) {
            throw new BizException(VoteErrorCode.ONLY_AUTHOR_CAN_START_VOTE);
        }

        //이미 게시된 투표 인지
        if (votingStartRepository.existsByPostId(postId)) {
            throw new BizException(VoteErrorCode.ALREADY_VOTING_START);
        }

    }

    private void validationGetVotingStart(long userId, long postId) {
        // userId가 postId 여행에 참가한 사람 인지
        if (participationRepository.findByPostEntityPostIdAndUserId(postId, String.valueOf(userId)).isEmpty()) {
            throw new BizException(VoteErrorCode.UNJOIN_TRAVEL);
        }

    }

    private void validationVoteVoting(long userId, long postId, long votingStartsId, VotingStartEntity votingStartEntity) {
        //votingStartsId가 postId의 투표인지
        if (!votingStartRepository.existsByIdAndPostId(votingStartsId, postId)) {
            throw new BizException(VoteErrorCode.VOTE_NOT_ALLOW_THIS_POST);
        }

        //이미 투표를 하였는지
        if (votingRepository.existsByUserIdAndVotingStartEntity(userId, votingStartEntity)) {
            throw new BizException(VoteErrorCode.ALREADY_VOTE);
        }

        //userId가 postId 여행에 참여하였는지
        if (participationRepository.findByPostEntityPostIdAndUserId(postId, String.valueOf(userId)).isEmpty()) {
            throw new BizException(VoteErrorCode.UNJOIN_TRAVEL);
        }
    }

    private void validationGetVote(long userId, long postId, long votingStartsId) {
        //votingStartsId가 postId의 투표인지
        if(!votingStartRepository.existsByIdAndPostId(votingStartsId, postId)) {
            throw new BizException(VoteErrorCode.VOTE_NOT_ALLOW_THIS_POST);
        }

        //userId가 postId 여행에 참여하였는지
        if (participationRepository.findByPostEntityPostIdAndUserId(postId, String.valueOf(userId)).isEmpty()) {
            throw new BizException(VoteErrorCode.UNJOIN_TRAVEL);
        }
    }
}
