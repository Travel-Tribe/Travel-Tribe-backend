package com.zerobase.travel.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.zerobase.travel.dto.request.ReportingRequestDto;
import com.zerobase.travel.entity.ReportingEntity;
import com.zerobase.travel.entity.VotingStartEntity;
import com.zerobase.travel.repository.ReportingRepository;
import com.zerobase.travel.repository.VotingStartRepository;
import com.zerobase.travel.type.VotingStatus;
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
}