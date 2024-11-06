package com.zerobase.travel.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.travel.dto.request.ReviewRequestDto;
import com.zerobase.travel.service.ReviewService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    void successCreateReview() throws Exception {

        //given
        long postId = 1L;

        ReviewRequestDto.CreateReview.File file1 = new ReviewRequestDto.CreateReview.File();
        file1.setFileAddress("/ca/asd/asd");

        ReviewRequestDto.CreateReview.File file2 = new ReviewRequestDto.CreateReview.File();
        file2.setFileAddress("/ca/asd/asd");

        ReviewRequestDto.CreateReview review = ReviewRequestDto.CreateReview.builder()
            .title("서울 여행")
            .contents("서울 여행 즐거워요.")
            .continent("ASIA")
            .country("KR")
            .region("서울")
            .files(List.of(file1, file2))
            .build();

        //when
        //then
        mockMvc.perform(post("/api/v1/posts/{postId}/reviews", postId)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(review))
                .header("X-User-Email", "test@test.com"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("SUCCESS"));

        verify(reviewService, times(1)).createReview(any(), any(), anyLong());

    }

    @Test
    @WithMockUser
    void successModifyReview() throws Exception {

        //given
        long postId = 1L;
        long reviewId = 1L;

        ReviewRequestDto.ModifyReview.File file1 = new ReviewRequestDto.ModifyReview.File();
        file1.setFileAddress("/ca/asd/asd");

        ReviewRequestDto.ModifyReview.File file2 = new ReviewRequestDto.ModifyReview.File();
        file2.setFileAddress("/ca/asd/asd");

        ReviewRequestDto.ModifyReview review = ReviewRequestDto.ModifyReview.builder()
            .title("서울 여행")
            .contents("서울 여행 즐거워요.")
            .continent("ASIA")
            .country("KR")
            .region("서울")
            .files(List.of(file1, file2))
            .build();

        //when
        //then
        mockMvc.perform(put("/api/v1/posts/{postId}/reviews/{reviewId}", postId, reviewId)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(review))
                .header("X-User-Email", "test@test.com"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("SUCCESS"));

        verify(reviewService, times(1)).modifyReview(any(), any(), anyLong(), anyLong());

    }

    @Test
    @WithMockUser
    void successDeleteReview() throws Exception {

        //given
        long postId = 1L;
        long reviewId = 1L;

        //when
        //then
        mockMvc.perform(delete("/api/v1/posts/{postId}/reviews/{reviewId}", postId, reviewId)
                .with(csrf())
                .header("X-User-Email", "test@test.com"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("SUCCESS"));

        verify(reviewService, times(1)).deleteReview(any(), anyLong(), anyLong());

    }
}