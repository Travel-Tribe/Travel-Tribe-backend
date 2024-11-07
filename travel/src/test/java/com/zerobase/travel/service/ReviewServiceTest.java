package com.zerobase.travel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.zerobase.travel.dto.request.ReviewRequestDto;
import com.zerobase.travel.dto.response.ReviewResponseDto.Review;
import com.zerobase.travel.entity.ReviewEntity;
import com.zerobase.travel.entity.ReviewFileEntity;
import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.errorcode.ReviewErrorCode;
import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import com.zerobase.travel.post.entity.UserClient;
import com.zerobase.travel.repository.ReviewFileRepository;
import com.zerobase.travel.repository.ReviewRepository;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import java.util.List;
import java.util.Optional;
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
    private ReviewFileRepository reviewFileRepository;

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

    @Test
    void successModifyReview() {
        //given
        long postId = 1L;
        long reviewId = 2L;
        long userId = 3L;
        String userEmail = "test@test.com";

        ReviewRequestDto.ModifyReview.File file1 = new ReviewRequestDto.ModifyReview.File();
        file1.setFileAddress("/ca/asd/asd");

        ReviewRequestDto.ModifyReview.File file2 = new ReviewRequestDto.ModifyReview.File();
        file2.setFileAddress("/ca/asd/asd1");

        ReviewRequestDto.ModifyReview review = ReviewRequestDto.ModifyReview.builder()
            .title("서울 여행")
            .contents("서울 여행 즐거워요.")
            .continent("ASIA")
            .country("KR")
            .region("서울")
            .files(List.of(file1, file2))
            .build();

        given(userClient.getUserInfoByEmail(any()))
            .willReturn(UserInfoResponseDTO.builder()
                .id(userId).build());

        given(reviewRepository.existsByIdAndUserId(anyLong(), anyLong()))
            .willReturn(true);

        given(reviewRepository.existsByIdAndPostId(anyLong(), anyLong()))
            .willReturn(true);

        given(reviewRepository.findById(anyLong()))
            .willReturn(Optional.of(
                    ReviewEntity.builder()
                        .id(reviewId)
                        .userId(userId)
                        .postId(postId)
                        .build()
                )
            );

        ArgumentCaptor<ReviewEntity> captor = ArgumentCaptor.forClass(ReviewEntity.class);

        //when
        reviewService.modifyReview(review, userEmail, postId, reviewId);

        //then
        verify(reviewRepository, times(1)).save(captor.capture());
        assertEquals(1L, captor.getValue().getPostId());
        assertEquals(2L, captor.getValue().getId());
        assertEquals(3L, captor.getValue().getUserId());
        assertEquals("ASIA", captor.getValue().getContinent().toString());
        assertEquals("KR", captor.getValue().getCountry().toString());
        assertEquals("서울", captor.getValue().getRegion());
        assertEquals("서울 여행", captor.getValue().getTitle());
        assertEquals("서울 여행 즐거워요.", captor.getValue().getContents());
        assertEquals("/ca/asd/asd", captor.getValue().getReviewFileList().get(0).getFileAddress());
        assertEquals("/ca/asd/asd1", captor.getValue().getReviewFileList().get(1).getFileAddress());
    }

    @Test
    @DisplayName("후기 수정 실패 - 내가 작성한 후기가 아님")
    void failModifyReview_1() {
        //given
        ReviewRequestDto.ModifyReview review = ReviewRequestDto.ModifyReview.builder().build();
        String userEmail = "test@test.com";
        long postId = 1L;
        long reviewId = 2L;

        given(userClient.getUserInfoByEmail(any()))
            .willReturn(
                UserInfoResponseDTO.builder()
                    .id(1L)
                    .build()
            );

        //when
        given(reviewRepository.existsByIdAndUserId(anyLong(), anyLong()))
            .willReturn(false);

        //then
        BizException ex = assertThrows(BizException.class, () -> reviewService.modifyReview(review, userEmail, postId, reviewId));
        assertEquals(ReviewErrorCode.NOT_MY_REVIEW, ex.getErrorCode());
    }

    @Test
    @DisplayName("후기 수정 실패 - 수정하는 후기가 postId에 해당하는 후기가 아님")
    void failModifyReview_2() {
        //given
        ReviewRequestDto.ModifyReview review = ReviewRequestDto.ModifyReview.builder().build();
        String userEmail = "test@test.com";
        long postId = 1L;
        long reviewId = 2L;

        given(userClient.getUserInfoByEmail(any()))
            .willReturn(
                UserInfoResponseDTO.builder()
                    .id(1L)
                    .build()
            );

        given(reviewRepository.existsByIdAndUserId(anyLong(), anyLong()))
            .willReturn(true);

        //when
        given(reviewRepository.existsByIdAndPostId(anyLong(), anyLong()))
            .willReturn(false);

        //then
        BizException ex = assertThrows(BizException.class, () -> reviewService.modifyReview(review, userEmail, postId, reviewId));
        assertEquals(ReviewErrorCode.THIS_REVIEW_NOT_IN_POST, ex.getErrorCode());
    }

    @Test
    void successDeleteReview() {
        //given
        long postId = 1L;
        long reviewId = 2L;
        long userId = 3L;
        String userEmail = "test@test.com";

        given(userClient.getUserInfoByEmail(any()))
            .willReturn(UserInfoResponseDTO.builder()
                .id(userId).build());

        given(reviewRepository.existsByIdAndUserId(anyLong(), anyLong()))
            .willReturn(true);

        given(reviewRepository.existsByIdAndPostId(anyLong(), anyLong()))
            .willReturn(true);

        given(reviewRepository.findById(anyLong()))
            .willReturn(Optional.of(
                    ReviewEntity.builder()
                        .id(reviewId)
                        .userId(userId)
                        .postId(postId)
                        .build()
                )
            );

        ArgumentCaptor<ReviewEntity> captor = ArgumentCaptor.forClass(ReviewEntity.class);

        //when
        reviewService.deleteReview(userEmail, postId, reviewId);

        //then
        verify(reviewRepository, times(1)).delete(captor.capture());
        assertEquals(reviewId, captor.getValue().getId());
    }

    @Test
    @DisplayName("후기 삭제 실패 - 내가 작성한 후기가 아님")
    void failDeleteReview_1() {
        //given
        String userEmail = "test@test.com";
        long postId = 1L;
        long reviewId = 2L;

        given(userClient.getUserInfoByEmail(any()))
            .willReturn(
                UserInfoResponseDTO.builder()
                    .id(1L)
                    .build()
            );

        //when
        given(reviewRepository.existsByIdAndUserId(anyLong(), anyLong()))
            .willReturn(false);

        //then
        BizException ex = assertThrows(BizException.class, () -> reviewService.deleteReview(userEmail, postId, reviewId));
        assertEquals(ReviewErrorCode.NOT_MY_REVIEW, ex.getErrorCode());
    }

    @Test
    @DisplayName("후기 삭제 실패 - 수정하는 후기가 postId에 해당하는 후기가 아님")
    void failDeleteReview_2() {
        //given
        String userEmail = "test@test.com";
        long postId = 1L;
        long reviewId = 2L;

        given(userClient.getUserInfoByEmail(any()))
            .willReturn(
                UserInfoResponseDTO.builder()
                    .id(1L)
                    .build()
            );

        given(reviewRepository.existsByIdAndUserId(anyLong(), anyLong()))
            .willReturn(true);

        //when
        given(reviewRepository.existsByIdAndPostId(anyLong(), anyLong()))
            .willReturn(false);

        //then
        BizException ex = assertThrows(BizException.class, () -> reviewService.deleteReview(userEmail, postId, reviewId));
        assertEquals(ReviewErrorCode.THIS_REVIEW_NOT_IN_POST, ex.getErrorCode());
    }

    @Test
    void successGetReview() {
        //given
        long postId = 1L;
        long reviewId = 2L;

        ReviewEntity reviewEntity = ReviewEntity.builder()
            .id(reviewId)
            .postId(postId)
            .userId(3L)
            .continent(Continent.ASIA)
            .country(Country.KR)
            .region("서울")
            .title("서울 여행")
            .contents("서울 여행 좋아요")
            .reviewFileList(
                List.of(
                    ReviewFileEntity.builder()
                        .fileAddress("/asd/asd/asd")
                        .build(),
                    ReviewFileEntity.builder()
                        .fileAddress("/zxc/zxc/zxc")
                        .build()
                )
            )
            .build();

        given(reviewRepository.findById(anyLong()))
            .willReturn(Optional.of(reviewEntity));

        given(reviewRepository.existsByIdAndPostId(anyLong(), anyLong()))
            .willReturn(true);

        //when
        Review review = reviewService.getReview(postId, reviewId);

        //then
        assertNotNull(review);

        assertEquals(reviewId, review.getReviewId());
        assertEquals(postId, review.getPostId());
        assertEquals(3L, review.getUserId());
        assertEquals(Continent.ASIA.toString(), review.getContinent());
        assertEquals(Country.KR.toString(), review.getCountry());
        assertEquals("서울", review.getRegion());
        assertEquals("서울 여행", review.getTitle());
        assertEquals("서울 여행 좋아요", review.getContents());
        assertEquals("/asd/asd/asd", review.getFiles().get(0).getFileAddress());
        assertEquals("/zxc/zxc/zxc", review.getFiles().get(1).getFileAddress());

    }

    @Test
    @DisplayName("후기 단건 조회 실패 - 후기가 없음")
    void failGetReview_1() {
        //given
        long postId = 1L;
        long reviewId = 2L;

        //when
        given(reviewRepository.findById(anyLong()))
            .willReturn(Optional.empty());

        //then
        BizException ex = assertThrows(BizException.class, () -> reviewService.getReview(postId, reviewId));
        assertEquals(ReviewErrorCode.NOT_FOUND_REVIEW, ex.getErrorCode());
    }

    @Test
    @DisplayName("후기 단건 조회 실패 - 조회한 후기가 해당 모집글의 후기가 아님")
    void failGetReview_2() {
        //given
        long postId = 1L;
        long reviewId = 2L;

        given(reviewRepository.findById(anyLong()))
            .willReturn(Optional.of(ReviewEntity.builder().build()));
        //when
        given(reviewRepository.existsByIdAndPostId(anyLong(), anyLong()))
            .willReturn(false);

        //then
        BizException ex = assertThrows(BizException.class, () -> reviewService.getReview(postId, reviewId));
        assertEquals(ReviewErrorCode.THIS_REVIEW_NOT_IN_POST, ex.getErrorCode());
    }

}