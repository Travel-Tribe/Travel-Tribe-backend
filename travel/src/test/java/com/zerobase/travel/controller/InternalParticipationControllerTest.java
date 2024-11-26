package com.zerobase.travel.controller;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.zerobase.travel.service.ParticipationManagementService;
import com.zerobase.travel.service.ParticipationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(InternalParticipationController.class)
class InternalParticipationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParticipationService participationService;

    @MockBean
    private ParticipationManagementService participationManagementService;

    @BeforeEach
    void setUp() {
        // Any setup if needed
    }

    @Test
    @WithMockUser
    void confirmParticipation_shouldReturnOk() throws Exception {
        mockMvc.perform(
                put("/internal/api/v1/posts/participations/{participationId}/success",
                    1L)
                    .with(csrf())
                    .header("X-User-Id", "user123")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(participationManagementService).successPaymentParticipation(1L,
            "user123");
    }

    @Test
    @WithMockUser
    void failParticipation_shouldReturnOk() throws Exception {
        mockMvc.perform(
                put("/internal/api/v1/posts/participations/{participationId}/fail",
                    1L)
                    .with(csrf())
                    .header("X-User-Id", "user123")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(participationManagementService).failedPaymentParticipation(1L,
            "user123");
    }

    @Test
    @WithMockUser
    void getParticipationsCompletedByUserId_shouldReturnCount()
        throws Exception {
        when(participationService.countParticipationsCompletedByUserId(
            "user123")).thenReturn(5);

        mockMvc.perform(
                get("/internal/api/v1/posts/participations/by-userid/{userId}",
                    "user123")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("5"));
    }

    @Test
    @WithMockUser
    void validateParticipationInfo_shouldReturnBoolean() throws Exception {
        when(participationService.validateParticipationInfoUserIdAndPostId(1L,
            1L, "user123")).thenReturn(true);

        mockMvc.perform(
                get("/internal/api/v1/posts/{postId}/participations/{participationId}/validate",
                    1L, 1L)
                    .with(csrf())
                    .header("X-User-Id", "user123")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("true"));
    }
}