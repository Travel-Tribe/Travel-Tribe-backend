package com.zerobase.travel.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.zerobase.travel.application.dto.ReviewFacadeDto;
import com.zerobase.travel.application.dto.ReviewFacadeDto.Review;
import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import com.zerobase.travel.post.entity.UserClient;
import com.zerobase.travel.post.service.UserClientService;
import com.zerobase.travel.post.type.Gender;
import com.zerobase.travel.post.type.MBTI;
import com.zerobase.travel.post.type.Smoking;
import com.zerobase.travel.post.type.UserStatus;
import com.zerobase.travel.repository.ReviewRepository;
import com.zerobase.travel.repository.specification.ReviewSearchDto;
import com.zerobase.travel.service.ReviewService;
import com.zerobase.travel.service.dto.ReviewServiceDto;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class ReviewFacadeTest {

    @Mock
    private ReviewService reviewService;

    @Mock
    private UserClient userClient;

    @InjectMocks
    private ReviewFacade reviewFacade;


    @Test
    void successGetReviews() {

        //given
        List<ReviewServiceDto.Review> reviewList = List.of(
            ReviewServiceDto.Review.builder()
                .reviewId(1L)
                .postId(2L)
                .userId(3L)
                .continent(Continent.ASIA)
                .country(Country.KR)
                .region("서울")
                .title("서울 여행")
                .contents("서울여행 좋아요.")
                .reviewFiles(List.of(
                    ReviewServiceDto.ReviewFile.builder()
                        .id(1L)
                        .fileAddress("/asd/asd/asd")
                        .build()
                ))
                .build(),
            ReviewServiceDto.Review.builder()
                .reviewId(2L)
                .postId(3L)
                .userId(4L)
                .continent(Continent.EUROPE)
                .country(Country.FR)
                .region("프랑스")
                .title("프랑스 여행")
                .contents("프랑스여행 좋아요.")
                .reviewFiles(List.of(
                    ReviewServiceDto.ReviewFile.builder()
                        .id(2L)
                        .fileAddress("/zxc/zxc/zxc")
                        .build()
                ))
                .build()
        );

        List<ResponseMessage<UserInfoResponseDTO>> userInfoResponseList = List.of(
            ResponseMessage.success(
                UserInfoResponseDTO.builder()
                    .id(3L)
                    .username("user123")
                    .nickname("닉네임123")
                    .phone("010-1234-5678")
                    .email("user123@example.com")
                    .status(UserStatus.ACTIVE)
                    .mbti(MBTI.ENFJ)
                    .smoking(Smoking.YES)
                    .introduction("안녕하세요! 반갑습니다.")
                    .gender(Gender.FEMALE)
                    .birth(LocalDate.of(1990, 1, 1))
                    .fileAddress("/qwe/qwe/qwe")
                    .ratingAvg(4.5)
                    .build())
            ,
            ResponseMessage.success(
                UserInfoResponseDTO.builder()
                    .id(4L)
                    .username("user456")
                    .nickname("닉네임456")
                    .phone("010-5678-1234")
                    .email("user456@example.com")
                    .status(UserStatus.INACTIVE)
                    .mbti(MBTI.INTJ)
                    .smoking(Smoking.NO)
                    .introduction("다들 안녕하세요!")
                    .gender(Gender.MALE)
                    .birth(LocalDate.of(1985, 5, 20))
                    .fileAddress("/asd/asd/asd")
                    .ratingAvg(3.8)
                    .build()
            )
        );

        Page<ReviewServiceDto.Review> reviewPage = new PageImpl<>(reviewList, PageRequest.of(0, 8), reviewList.size());

        given(reviewService.getReviews(any(), any()))
            .willReturn(reviewPage);

        given(userClient.searchUserInfo(any(), any()))
            .willReturn(userInfoResponseList.get(0), userInfoResponseList.get(1));

        //when
        Page<ReviewFacadeDto.Review> reviews = reviewFacade.getReviews(ReviewSearchDto.builder().build(), PageRequest.of(0, 8));

        //then
        assertNotNull(reviews);
        assertEquals(0, reviews.getNumber());
        assertEquals(8, reviews.getSize());
        assertEquals(2, reviews.getTotalElements());
        assertEquals(1, reviews.getTotalPages());
        assertTrue(reviews.isLast());

        // Review 1 검증
        ReviewFacadeDto.Review review1 = reviews.getContent().get(0);
        assertEquals(1L, review1.getReviewId());
        assertEquals(2L, review1.getPostId());
        assertEquals(3L, review1.getUserId());
        assertEquals(Continent.ASIA, review1.getContinent());
        assertEquals(Country.KR, review1.getCountry());
        assertEquals("서울", review1.getRegion());
        assertEquals("서울 여행", review1.getTitle());
        assertEquals("서울여행 좋아요.", review1.getContents());
        assertEquals(1L, review1.getReviewFiles().get(0).getId());
        assertEquals("/asd/asd/asd", review1.getReviewFiles().get(0).getFileAddress());
        assertEquals(3L, review1.getUser().getId());
        assertEquals("user123", review1.getUser().getUsername());
        assertEquals("닉네임123", review1.getUser().getNickname());
        assertEquals("010-1234-5678", review1.getUser().getPhone());
        assertEquals("user123@example.com", review1.getUser().getEmail());
        assertEquals(UserStatus.ACTIVE, review1.getUser().getStatus());
        assertEquals(MBTI.ENFJ, review1.getUser().getMbti());
        assertEquals(Smoking.YES, review1.getUser().getSmoking());
        assertEquals("안녕하세요! 반갑습니다.", review1.getUser().getIntroduction());
        assertEquals(Gender.FEMALE, review1.getUser().getGender());
        assertEquals(LocalDate.of(1990, 1, 1), review1.getUser().getBirth());
        assertEquals("/qwe/qwe/qwe", review1.getUser().getFileAddress());
        assertEquals(4.5, review1.getUser().getRatingAvg());


        // Review 2 검증
        ReviewFacadeDto.Review review2 = reviews.getContent().get(1);
        assertEquals(2L, review2.getReviewId());
        assertEquals(3L, review2.getPostId());
        assertEquals(4L, review2.getUserId());
        assertEquals(Continent.EUROPE, review2.getContinent());
        assertEquals(Country.FR, review2.getCountry());
        assertEquals("프랑스", review2.getRegion());
        assertEquals("프랑스 여행", review2.getTitle());
        assertEquals("프랑스여행 좋아요.", review2.getContents());
        assertEquals(2L, review2.getReviewFiles().get(0).getId());
        assertEquals("/zxc/zxc/zxc", review2.getReviewFiles().get(0).getFileAddress());
        assertEquals(4L, review2.getUser().getId());
        assertEquals("user456", review2.getUser().getUsername());
        assertEquals("닉네임456", review2.getUser().getNickname());
        assertEquals("010-5678-1234", review2.getUser().getPhone());
        assertEquals("user456@example.com", review2.getUser().getEmail());
        assertEquals(UserStatus.INACTIVE, review2.getUser().getStatus());
        assertEquals(MBTI.INTJ, review2.getUser().getMbti());
        assertEquals(Smoking.NO, review2.getUser().getSmoking());
        assertEquals("다들 안녕하세요!", review2.getUser().getIntroduction());
        assertEquals(Gender.MALE, review2.getUser().getGender());
        assertEquals(LocalDate.of(1985, 5, 20), review2.getUser().getBirth());
        assertEquals("/asd/asd/asd", review2.getUser().getFileAddress());
        assertEquals(3.8, review2.getUser().getRatingAvg());
    }
}