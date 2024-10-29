package com.zerobase.travel.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.travel.dto.request.VoteRequestDto.VoteVoting;
import com.zerobase.travel.dto.response.VoteResponseDto;
import com.zerobase.travel.dto.response.VoteResponseDto.GetVote;
import com.zerobase.travel.service.VoteService;
import com.zerobase.travel.type.VotingStatus;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(VoteController.class)
class VoteControllerTest {

    @MockBean
    private VoteService voteService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createVote() throws Exception {

        //given
        long postId = 1L;

        //when
        //then
        mockMvc.perform(post("/api/v1/posts/{postId}/voting-starts", postId))
            .andExpect(status().isOk());

        verify(voteService, times(1)).createVote(anyLong(), anyLong());

    }

    //TODO 김용민 votingStart 조회 테스트 작성하기
    @Test
    void getVotingStart() throws Exception {

        //given
        long postId = 1L;
        long userId = 1L;

        VoteResponseDto.VotingStart votingStart = VoteResponseDto.VotingStart.builder()
            .votingStartsId(1L)
            .votingStatus(VotingStatus.STARTING.toString())
            .build();

        given(voteService.getVotingStart(anyLong(), anyLong()))
            .willReturn(votingStart);

        //when
        //then
        mockMvc.perform(get("/api/v1/posts/{postId}/voting-starts", postId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.votingStartsId").value(1L))
            .andExpect(jsonPath("$.votingStatus").value(VotingStatus.STARTING.toString()))
            .andReturn();

        verify(voteService, times(1)).getVotingStart(anyLong(), anyLong());

    }


    @Test
    void voteVoting() throws Exception {

        //given
        long userId = 1L;
        long postId = 1L;
        long votingStartsId = 2L;

        VoteVoting voteVoting = new VoteVoting();
        voteVoting.setApproval(true);


        //when
        //then
        mockMvc.perform(post("/api/v1/posts/{postId}/voting-starts/{votingStartsId}/votings", postId, votingStartsId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(voteVoting)))
            .andExpect(status().isOk());

        verify(voteService, times(1)).voteVoting(anyLong(), anyLong(), anyLong(), anyBoolean());

    }

    @Test
    void getVote() throws Exception {

        //given
        long postId = 1L;
        long votingStartsId = 1L;

        VoteResponseDto.GetVote getVote = GetVote.builder()
            .votings(
                List.of(
                    VoteResponseDto.GetVote.Vote.builder()
                        .votingId(1L)
                        .votingStartId(votingStartsId)
                        .userId(1L)
                        .approval(true)
                        .build(),
                    VoteResponseDto.GetVote.Vote.builder()
                        .votingId(2L)
                        .votingStartId(votingStartsId)
                        .userId(2L)
                        .approval(false)
                        .build()
                )
            )
            .build();

        given(voteService.getVote(anyLong(), anyLong(), anyLong()))
            .willReturn(getVote);

        //when
        //then
        mockMvc.perform(get("/api/v1/posts/{postId}/voting-starts/{votingStartsId}/votings", postId, votingStartsId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.votings[0].votingId").value(1L))
            .andExpect(jsonPath("$.votings[0].votingStartId").value(votingStartsId))
            .andExpect(jsonPath("$.votings[0].userId").value(1L))
            .andExpect(jsonPath("$.votings[0].approval").value(true))
            .andExpect(jsonPath("$.votings[1].votingId").value(2L))
            .andExpect(jsonPath("$.votings[1].votingStartId").value(votingStartsId))
            .andExpect(jsonPath("$.votings[1].userId").value(2L))
            .andExpect(jsonPath("$.votings[1].approval").value(false))
            .andReturn();

        verify(voteService, times(1)).getVote(anyLong(), anyLong(), anyLong());

    }
}