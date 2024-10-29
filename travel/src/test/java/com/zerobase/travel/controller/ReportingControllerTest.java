package com.zerobase.travel.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.travel.dto.request.GiveRatingDto;
import com.zerobase.travel.dto.request.ReportingRequestDto;
import com.zerobase.travel.service.RatingService;
import com.zerobase.travel.service.ReportingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ReportingController.class)
class ReportingControllerTest {

    @MockBean
    private ReportingService reportingService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void reportingUser() throws Exception {

        //given
        long postId = 1L;
        ReportingRequestDto.ReportUser reportUser = ReportingRequestDto.ReportUser.builder()
            .receiverUserId(2L)
            .comment("이사람 이상해요.")
            .build();

        //when
        //then
        mockMvc.perform(post("/api/v1/posts/{postId}/reporting", postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reportUser)))
            .andExpect(status().isOk());

        verify(reportingService, times(1)).reportingUser(any(), anyLong(), anyLong());

    }

}