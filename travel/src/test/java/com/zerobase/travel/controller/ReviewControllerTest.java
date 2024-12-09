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
import com.zerobase.travel.application.ReviewFacade;
import com.zerobase.travel.application.dto.ReviewFacadeDto;
import com.zerobase.travel.dto.request.ReviewRequestDto;
import com.zerobase.travel.dto.response.ReviewResponseDto;
import com.zerobase.travel.post.type.MBTI;
import com.zerobase.travel.service.ReviewService;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @MockBean
    private ReviewService reviewService;

    @MockBean
    private ReviewFacade reviewFacade;

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

    @Test
    @WithMockUser
    void successGetReviewPage() throws Exception {

        // given
        List<ReviewFacadeDto.Review> reviewList = List.of(
            ReviewFacadeDto.Review.builder()
                .reviewId(1L)
                .postId(2L)
                .userId(3L)
                .continent(Continent.AFRICA)
                .country(Country.KR)
                .region("서울")
                .title("서울 여행")
                .contents("서울여행 좋아요.")
                .reviewFiles(List.of(
                    ReviewFacadeDto.ReviewFile.builder()
                        .fileAddress("/asd/asd/asd")
                        .build()
                ))
                .user(
                    ReviewFacadeDto.User.builder()
                        .nickname("닉네임1")
                        .mbti(MBTI.ENFJ)
                        .fileAddress("/zxc/zxc/zxc")
                        .build()
                )
                .build(),
            ReviewFacadeDto.Review.builder()
                .reviewId(2L)
                .postId(3L)
                .userId(4L)
                .continent(Continent.EUROPE)
                .country(Country.FR)
                .region("파리")
                .title("파리 여행")
                .contents("파리여행 추천해요.")
                .reviewFiles(List.of(
                    ReviewFacadeDto.ReviewFile.builder()
                        .fileAddress("/qwe/qwe/qwe")
                        .build()
                ))
                .user(
                    ReviewFacadeDto.User.builder()
                        .nickname("닉네임2")
                        .mbti(MBTI.ISTP)
                        .fileAddress("/asd/asd/asd")
                        .build()
                )
                .build(),
            ReviewFacadeDto.Review.builder()
                .reviewId(3L)
                .postId(4L)
                .userId(5L)
                .continent(Continent.ASIA)
                .country(Country.JP)
                .region("도쿄")
                .title("도쿄 여행")
                .contents("도쿄여행 최고에요.")
                .reviewFiles(List.of(
                    ReviewFacadeDto.ReviewFile.builder()
                        .fileAddress("/xyz/xyz/xyz")
                        .build()
                ))
                .user(
                    ReviewFacadeDto.User.builder()
                        .nickname("닉네임3")
                        .mbti(MBTI.INFP)
                        .fileAddress("/qwe/qwe/qwe")
                        .build()
                )
                .build()
        );

        Page<ReviewFacadeDto.Review> reviewPage = new PageImpl<>(reviewList, PageRequest.of(0, 8), reviewList.size());

        given(reviewFacade.getReviews(any(), any()))
            .willReturn(reviewPage);

        // when
        // then
        mockMvc.perform(get("/api/v1/reviews"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("SUCCESS"))
            .andExpect(jsonPath("$.data.pageNumber").value(0))
            .andExpect(jsonPath("$.data.pageSize").value(8))
            .andExpect(jsonPath("$.data.totalElements").value(reviewList.size()))
            .andExpect(jsonPath("$.data.totalPages").value(1))
            .andExpect(jsonPath("$.data.last").value(true))

            // Review 1
            .andExpect(jsonPath("$.data.reviews[0].reviewId").value(1L))
            .andExpect(jsonPath("$.data.reviews[0].postId").value(2L))
            .andExpect(jsonPath("$.data.reviews[0].userId").value(3L))
            .andExpect(jsonPath("$.data.reviews[0].continent").value(Continent.AFRICA.toString()))
            .andExpect(jsonPath("$.data.reviews[0].country").value(Country.KR.toString()))
            .andExpect(jsonPath("$.data.reviews[0].region").value("서울"))
            .andExpect(jsonPath("$.data.reviews[0].title").value("서울 여행"))
            .andExpect(jsonPath("$.data.reviews[0].contents").value("서울여행 좋아요."))
            .andExpect(jsonPath("$.data.reviews[0].files[0].fileAddress").value("/asd/asd/asd"))
            .andExpect(jsonPath("$.data.reviews[0].nickname").value("닉네임1"))
            .andExpect(jsonPath("$.data.reviews[0].mbti").value(MBTI.ENFJ.toString()))
            .andExpect(jsonPath("$.data.reviews[0].profileFileAddress").value("/zxc/zxc/zxc"))

            // Review 2
            .andExpect(jsonPath("$.data.reviews[1].reviewId").value(2L))
            .andExpect(jsonPath("$.data.reviews[1].postId").value(3L))
            .andExpect(jsonPath("$.data.reviews[1].userId").value(4L))
            .andExpect(jsonPath("$.data.reviews[1].continent").value(Continent.EUROPE.toString()))
            .andExpect(jsonPath("$.data.reviews[1].country").value(Country.FR.toString()))
            .andExpect(jsonPath("$.data.reviews[1].region").value("파리"))
            .andExpect(jsonPath("$.data.reviews[1].title").value("파리 여행"))
            .andExpect(jsonPath("$.data.reviews[1].contents").value("파리여행 추천해요."))
            .andExpect(jsonPath("$.data.reviews[1].files[0].fileAddress").value("/qwe/qwe/qwe"))
            .andExpect(jsonPath("$.data.reviews[1].nickname").value("닉네임2"))
            .andExpect(jsonPath("$.data.reviews[1].mbti").value(MBTI.ISTP.toString()))
            .andExpect(jsonPath("$.data.reviews[1].profileFileAddress").value("/asd/asd/asd"))

            // Review 3
            .andExpect(jsonPath("$.data.reviews[2].reviewId").value(3L))
            .andExpect(jsonPath("$.data.reviews[2].postId").value(4L))
            .andExpect(jsonPath("$.data.reviews[2].userId").value(5L))
            .andExpect(jsonPath("$.data.reviews[2].continent").value(Continent.ASIA.toString()))
            .andExpect(jsonPath("$.data.reviews[2].country").value(Country.JP.toString()))
            .andExpect(jsonPath("$.data.reviews[2].region").value("도쿄"))
            .andExpect(jsonPath("$.data.reviews[2].title").value("도쿄 여행"))
            .andExpect(jsonPath("$.data.reviews[2].contents").value("도쿄여행 최고에요."))
            .andExpect(jsonPath("$.data.reviews[2].files[0].fileAddress").value("/xyz/xyz/xyz"))
            .andExpect(jsonPath("$.data.reviews[2].nickname").value("닉네임3"))
            .andExpect(jsonPath("$.data.reviews[2].mbti").value(MBTI.INFP.toString()))
            .andExpect(jsonPath("$.data.reviews[2].profileFileAddress").value("/qwe/qwe/qwe"));

        verify(reviewFacade, times(1)).getReviews(any(), any());
    }
}