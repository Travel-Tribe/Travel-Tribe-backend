package com.zerobase.travel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.zerobase.travel.dto.request.ReviewRequestDto;
import com.zerobase.travel.entity.ReviewEntity;
import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.errorcode.ReviewErrorCode;
import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import com.zerobase.travel.post.entity.UserClient;
import com.zerobase.travel.repository.ReviewRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private UserClient userClient;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    void successCreateReview() {
        //given
        long postId = 1L;
        String userEmail = "test@test.com";

        ReviewRequestDto.CreateReview.File file1 = new ReviewRequestDto.CreateReview.File();
        file1.setFileAddress("/ca/asd/asd");

        ReviewRequestDto.CreateReview.File file2 = new ReviewRequestDto.CreateReview.File();
        file2.setFileAddress("/ca/asd/asd1");

        ReviewRequestDto.CreateReview review = ReviewRequestDto.CreateReview.builder()
            .title("서울 여행")
            .contents("서울 여행 즐거워요.")
            .continent("ASIA")
            .country("KR")
            .region("서울")
            .files(List.of(file1, file2))
            .build();

        given(userClient.getUserInfoByEmail(any()))
            .willReturn(UserInfoResponseDTO.builder()
                .id(2L).build());

        given(reviewRepository.existsByUserIdAndPostId(anyLong(), anyLong()))
            .willReturn(false);

        ArgumentCaptor<ReviewEntity> captor = ArgumentCaptor.forClass(ReviewEntity.class);

        //when
        reviewService.createReview(review, userEmail, postId);

        //then
        verify(reviewRepository, times(1)).save(captor.capture());
        assertEquals(1L, captor.getValue().getPostId());
        assertEquals(2L, captor.getValue().getUserId());
        assertEquals("ASIA", captor.getValue().getContinent().toString());
        assertEquals("KR", captor.getValue().getCountry().toString());
        assertEquals("서울", captor.getValue().getRegion());
        assertEquals("서울 여행", captor.getValue().getTitle());
        assertEquals("서울 여행 즐거워요.", captor.getValue().getContents());
        assertEquals("/ca/asd/asd", captor.getValue().getReviewFileList().get(0).getFileAddress());
        assertEquals("/ca/asd/asd1", captor.getValue().getReviewFileList().get(1).getFileAddress());
    }

    @Test
    @DisplayName("후기 작성 실패 - 이미 존재하는 후기")
    void failCreateReview() {
        //given
        ReviewRequestDto.CreateReview review = ReviewRequestDto.CreateReview.builder().build();
        String userEmail = "test@test.com";
        long postId = 1L;

        given(userClient.getUserInfoByEmail(any()))
            .willReturn(
                UserInfoResponseDTO.builder()
                    .id(1L)
                    .build()
            );

        //when
        given(reviewRepository.existsByUserIdAndPostId(anyLong(), anyLong()))
            .willReturn(true);

        //then
        BizException ex = assertThrows(BizException.class, () -> reviewService.createReview(review, userEmail, postId));
        assertEquals(ReviewErrorCode.ALREADY_WRITE_REVIEW, ex.getErrorCode());
    }
}