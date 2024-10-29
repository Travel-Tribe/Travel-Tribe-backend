package com.zerobase.travel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.zerobase.travel.dto.response.VoteResponseDto.VotingStart;
import com.zerobase.travel.entity.VotingStartEntity;
import com.zerobase.travel.repository.VotingStartRepository;
import com.zerobase.travel.type.VotingStatus;
import java.util.Optional;
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

    @InjectMocks
    private VoteService voteService;

    @Test
    void createVote() {
        //given
        long postId = 1L;
        long userId = 2L;

        ArgumentCaptor<VotingStartEntity> captor = ArgumentCaptor.forClass(VotingStartEntity.class);

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

        given(votingStartRepository.findByPostId(anyLong()))
            .willReturn(Optional.of(votingStartEntity));


        //when
        VotingStart votingStart = voteService.getVotingStart(userId, postId);

        //then
        assertEquals(VotingStatus.STARTING.toString(), votingStart.getVotingStatus());
        assertEquals(1L, votingStart.getVotingStartsId());
    }


}