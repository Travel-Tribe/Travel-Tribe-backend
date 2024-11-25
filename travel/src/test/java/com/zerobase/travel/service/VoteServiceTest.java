package com.zerobase.travel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.zerobase.travel.dto.response.VoteResponseDto.GetVote;
import com.zerobase.travel.dto.response.VoteResponseDto.VotingStart;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.entity.VotingEntity;
import com.zerobase.travel.entity.VotingStartEntity;
import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.errorcode.VoteErrorCode;
import com.zerobase.travel.post.repository.PostRepository;
import com.zerobase.travel.repository.ParticipationRepository;
import com.zerobase.travel.repository.VotingRepository;
import com.zerobase.travel.repository.VotingStartRepository;
import com.zerobase.travel.type.VotingStatus;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VoteServiceTest {

    @Mock
    private VotingStartRepository votingStartRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private VotingRepository votingRepository;

    @Mock
    private ParticipationRepository participationRepository;

    @InjectMocks
    private VoteService voteService;

    @Test
    void createVote() {
        //given
        long postId = 1L;
        long userId = 2L;

        ArgumentCaptor<VotingStartEntity> captor = ArgumentCaptor.forClass(VotingStartEntity.class);

        given(postRepository.existsByPostIdAndUserId(anyLong(), anyLong()))
            .willReturn(true);

        given(votingStartRepository.existsByPostId(anyLong()))
            .willReturn(false);

        //when
        voteService.createVote(userId, postId);

        //then
        verify(votingStartRepository, times(1)).save(captor.capture());
        assertEquals(VotingStatus.STARTING, captor.getValue().getVotingStatus());
        assertEquals(1L, captor.getValue().getPostId());
    }

    @Test
    void getVotingStart() {
        //given
        long id = 1L;
        long postId = 2L;
        long userId = 3L;

        VotingStartEntity votingStartEntity = VotingStartEntity.builder()
            .id(id)
            .postId(postId)
            .votingStatus(VotingStatus.STARTING)
            .build();

        given(participationRepository.findByPostEntityPostIdAndUserId(anyLong(), any()))
            .willReturn(Optional.of(ParticipationEntity.builder().build()));

        given(votingStartRepository.findByPostId(anyLong()))
            .willReturn(Optional.of(votingStartEntity));

        //when
        VotingStart votingStart = voteService.getVotingStart(userId, postId);

        //then
        assertEquals(VotingStatus.STARTING.toString(), votingStart.getVotingStatus());
        assertEquals(1L, votingStart.getVotingStartsId());
    }

    @Test
    void voteVoting() throws Exception {

        //given
        long postId = 1L;
        long userId = 2L;
        long votingStartsId = 1L;
        boolean approval = true;

        VotingStartEntity votingStartEntity = VotingStartEntity.builder()
            .postId(postId)
            .build();

        ArgumentCaptor<VotingEntity> captor = ArgumentCaptor.forClass(VotingEntity.class);

        given(participationRepository.findByPostEntityPostIdAndUserId(postId, String.valueOf(userId)))
            .willReturn(Optional.of(ParticipationEntity.builder().build()));

        given(votingStartRepository.findByPostId(anyLong()))
            .willReturn(Optional.of(votingStartEntity));

        given(votingStartRepository.existsByIdAndPostId(anyLong(), anyLong()))
            .willReturn(true);

        //when
        voteService.voteVoting(userId, postId, votingStartsId, approval);

        //then
        verify(votingRepository, times(1)).save(captor.capture());
        assertEquals(approval, captor.getValue().getApproval());
        assertEquals(userId, captor.getValue().getUserId());
        assertEquals(postId, captor.getValue().getVotingStartEntity().getPostId());

    }

    @Test
    void getVote() {
        //given
        long postId = 1L;
        long userId = 2L;
        long votingStartsId = 1L;

        VotingStartEntity votingStartEntity = VotingStartEntity.builder()
            .id(votingStartsId)
            .build();

        List<VotingEntity> votingEntityList = List.of(
            VotingEntity.builder()
                .id(1L)
                .votingStartEntity(votingStartEntity)
                .userId(1L)
                .approval(true)
                .build(),
            VotingEntity.builder()
                .id(2L)
                .votingStartEntity(votingStartEntity)
                .userId(2L)
                .approval(false)
                .build()
        );

        given(votingStartRepository.existsByIdAndPostId(votingStartsId, postId))
            .willReturn(true);

        given(participationRepository.findByPostEntityPostIdAndUserId(postId, String.valueOf(userId)))
            .willReturn(Optional.of(ParticipationEntity.builder().build()));

        given(votingStartRepository.findById(anyLong()))
            .willReturn(Optional.of(votingStartEntity));

        given(votingRepository.findAllByVotingStartEntity(any()))
            .willReturn(votingEntityList);

        //when
        GetVote vote = voteService.getVote(userId, postId, votingStartsId);

        //then
        assertNotNull(vote);
        assertEquals(2, vote.getVotings().size());

        assertEquals(1L, vote.getVotings().get(0).getVotingId());
        assertEquals(1L, vote.getVotings().get(0).getVotingStartId());
        assertEquals(1L, vote.getVotings().get(0).getUserId());
        assertTrue(vote.getVotings().get(0).getApproval());

        assertEquals(2L, vote.getVotings().get(1).getVotingId());
        assertEquals(1L, vote.getVotings().get(0).getVotingStartId());
        assertEquals(2L, vote.getVotings().get(1).getUserId());
        assertFalse(vote.getVotings().get(1).getApproval());
    }

    @Test
    @DisplayName("투표 개시 실패 - 이미 존재하는 투표 개시")
    void createVoteFailBy() {
        //given
        long postId = 1L;
        long userId = 2L;

        given(postRepository.existsByPostIdAndUserId(anyLong(), anyLong()))
            .willReturn(true);

        //when
        given(votingStartRepository.existsByPostId(anyLong()))
            .willReturn(true);

        //then
        BizException ex = assertThrows(BizException.class, () -> voteService.createVote(userId, postId));
        assertEquals(VoteErrorCode.ALREADY_VOTING_START, ex.getErrorCode());
    }

    @Test
    @DisplayName("투표 개시 실패 - 모집 게시글의 주최자가 아닙니다")
    void createVoteFailBy주최자가아닙니다() {
        //given
        long postId = 1L;
        long userId = 2L;

        //when
        given(postRepository.existsByPostIdAndUserId(anyLong(), anyLong()))
            .willReturn(false);

        //then
        BizException ex = assertThrows(BizException.class, () -> voteService.createVote(userId, postId));
        assertEquals(VoteErrorCode.ONLY_AUTHOR_CAN_START_VOTE, ex.getErrorCode());
    }

    @Test
    @DisplayName("투표 실패 - 이미 존재하는 투표")
    void voteVotingFailBy이미_존재하는_투표() {
        //given
        long postId = 1L;
        long userId = 2L;
        long votingStartsId = 1L;
        boolean approval = true;

        VotingStartEntity votingStartEntity = VotingStartEntity.builder()
            .build();

        given(votingStartRepository.findByPostId(anyLong()))
            .willReturn(Optional.of(votingStartEntity));

        given(votingStartRepository.existsByIdAndPostId(anyLong(), anyLong()))
            .willReturn(true);

        //when
        given(votingRepository.existsByUserIdAndVotingStartEntity(anyLong(), any()))
            .willReturn(true);

        //then
        BizException ex = assertThrows(BizException.class, () -> voteService.voteVoting(userId, postId, votingStartsId, approval));
        assertEquals(VoteErrorCode.ALREADY_VOTE, ex.getErrorCode());
    }

    @Test
    @DisplayName("투표 실패 - 게시글에 해당하는 투표가 아님")
    void voteVotingFailBy게시글에해당하는투표가아님() {
        //given
        long postId = 1L;
        long userId = 2L;
        long votingStartsId = 1L;
        boolean approval = true;

        VotingStartEntity votingStartEntity = VotingStartEntity.builder()
            .build();

        given(votingStartRepository.findByPostId(anyLong()))
            .willReturn(Optional.of(votingStartEntity));

        //when
        given(votingStartRepository.existsByIdAndPostId(anyLong(), anyLong()))
            .willReturn(false);

        //then
        BizException ex = assertThrows(BizException.class, () -> voteService.voteVoting(userId, postId, votingStartsId, approval));
        assertEquals(VoteErrorCode.VOTE_NOT_ALLOW_THIS_POST, ex.getErrorCode());
    }

}