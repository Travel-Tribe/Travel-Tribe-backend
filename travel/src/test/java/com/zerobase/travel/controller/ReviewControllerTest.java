package com.zerobase.travel.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.travel.dto.request.ReviewRequestDto;
import com.zerobase.travel.dto.response.ReviewResponseDto;
import com.zerobase.travel.service.ReviewService;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
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

    @Test
    @WithMockUser
    void successGetReview() throws Exception {

        //given
        ReviewResponseDto.Review getReview = ReviewResponseDto.Review.builder()
            .reviewId(1L)
            .postId(2L)
            .userId(3L)
            .continent(Continent.AFRICA.toString())
            .country(Country.KR.toString())
            .region("서울")
            .title("서울 여행")
            .contents("서울여행 좋아요.")
            .files(
                List.of(
                    ReviewResponseDto.ReviewFile.builder()
                        .fileAddress("/asd/asd/asd")
                        .build(),
                    ReviewResponseDto.ReviewFile.builder()
                        .fileAddress("/zxc/zxc/zxc")
                        .build()
                )
            )
            .build();

        given(reviewService.getReview(anyLong(), anyLong()))
            .willReturn(getReview);

        //when
        //then
        mockMvc.perform(get("/api/v1/posts/{postId}/reviews/{reviewId}", 1L, 2L))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("SUCCESS"))
            .andExpect(jsonPath("$.data.reviewId").value(1L))
            .andExpect(jsonPath("$.data.postId").value(2L))
            .andExpect(jsonPath("$.data.userId").value(3L))
            .andExpect(jsonPath("$.data.continent").value(Continent.AFRICA.toString()))
            .andExpect(jsonPath("$.data.country").value(Country.KR.toString()))
            .andExpect(jsonPath("$.data.region").value("서울"))
            .andExpect(jsonPath("$.data.title").value("서울 여행"))
            .andExpect(jsonPath("$.data.contents").value("서울여행 좋아요."))
            .andExpect(jsonPath("$.data.files[0].fileAddress").value("/asd/asd/asd"))
            .andExpect(jsonPath("$.data.files[1].fileAddress").value("/zxc/zxc/zxc"));

        verify(reviewService, times(1)).getReview(anyLong(), anyLong());

    }
}