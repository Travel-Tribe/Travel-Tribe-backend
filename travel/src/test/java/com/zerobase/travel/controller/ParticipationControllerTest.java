package com.zerobase.travel.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.travel.dto.ParticipationDto;
import com.zerobase.travel.dto.ResponseMyParticipationsDto;
import com.zerobase.travel.dto.ResponseParticipationsByPostDto;
import com.zerobase.travel.service.ParticipationManagementService;
import com.zerobase.travel.service.ParticipationService;
import com.zerobase.travel.type.DepositStatus;
import com.zerobase.travel.type.ParticipationStatus;
import com.zerobase.travel.type.RatingStatus;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ParticipationController.class)
public class ParticipationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ParticipationManagementService participationManagementService;

    @MockBean
    private ParticipationService participationService;

    @Test
    @WithMockUser
    public void testReadyParticipationSuccess() throws Exception {
        long postId = 1L;
        String userId = "user1";
        String userEmail = "user1@example.com";
        Long participationId = 1L;

        ParticipationDto participationDto = ParticipationDto.builder()
            .participationId(participationId)
            .postId(postId)
            .userId(userId)
            .participationStatus(ParticipationStatus.JOIN_READY.getKorean())
            .depositStatus(DepositStatus.UNPAID.getKorean())
            .ratingStatus(RatingStatus.NOT_RATED.getKorean())
            .depositReturnDate(null)
            .build();

        when(participationManagementService.readyParticipation(anyLong(), anyString(), anyString()))
            .thenReturn(participationDto);

        mockMvc.perform(post("/api/v1/posts/{postId}/participations", postId)
                .with(csrf())
                .header("X-User-Id", userId)
                .header("X-User-Email", userEmail)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.participationId").value(participationId))
            .andExpect(jsonPath("$.data.postId").value(postId))
            .andExpect(jsonPath("$.data.userId").value(userId))
            .andExpect(jsonPath("$.data.participationStatus").value(ParticipationStatus.JOIN_READY.getKorean()))
            .andExpect(jsonPath("$.data.depositStatus").value(DepositStatus.UNPAID.getKorean()))
            .andExpect(jsonPath("$.data.ratingStatus").value(RatingStatus.NOT_RATED.getKorean()))
            .andExpect(jsonPath("$.data.depositReturnDate").doesNotExist());

    }

    @Test
    @WithMockUser
    public void testDeleteParticipationsSuccess() throws Exception {
        long postId = 1L;
        String userId = "user1";

        mockMvc.perform(delete("/api/v1/posts/{postId}/participations", postId)
                .with(csrf())
                .header("X-User-Id", userId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    @WithMockUser

    public void testGetParticipationsByPostSuccess() throws Exception {
        long postId = 1L;
        List<ResponseParticipationsByPostDto> participations = new ArrayList<>();

        when(participationService.getParticipationsDtosByPostIdAndStatusOfJoin(anyLong()))
            .thenReturn(participations);

        mockMvc.perform(get("/api/v1/posts/{postId}/participations", postId)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    @WithMockUser
    public void testGetMyParticipationsStatusofJoninAndJoinReadySuccess() throws Exception {
        String userId = "user1";
        List<ResponseMyParticipationsDto> participations = new ArrayList<>();

        when(participationService.getMyParticipationsStatusOfJoinAndJoinReady(anyString()))
            .thenReturn(participations);

        mockMvc.perform(get("/api/v1/posts/participations")
                .with(csrf())
                .header("X-User-Id", userId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data").isEmpty());
    }
}