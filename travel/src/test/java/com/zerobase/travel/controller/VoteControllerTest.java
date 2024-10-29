package com.zerobase.travel.controller;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.travel.dto.request.VoteRequestDto.VoteVoting;
import com.zerobase.travel.service.VoteService;
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
}