package com.zerobase.travel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.zerobase.travel.api.UserApi;
import com.zerobase.travel.common.response.ResponseMessage;
import com.zerobase.travel.dto.request.GiveRatingDto;
import com.zerobase.travel.entity.ParticipationEntity;
import com.zerobase.travel.entity.RatingEntity;
import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.errorcode.RatingErrorCode;
import com.zerobase.travel.repository.ParticipationRepository;
import com.zerobase.travel.repository.RatingRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private ParticipationRepository participationRepository;

    @Mock
    private UserApi userApi;

    @InjectMocks
    private RatingService ratingService;

    @Test
    void giveRating() {

        //given
        long postId = 1L;
        long senderUserId = 2L;

        GiveRatingDto giveRatingDto = GiveRatingDto.builder()
            .score(5.0)
            .receiverId(3L)
            .build();

        ArgumentCaptor<RatingEntity> captor = ArgumentCaptor.forClass(RatingEntity.class);

        given(userApi.updateUserRating(anyLong(), anyDouble()))
            .willReturn(ResponseMessage.<Void>builder()
                .result("SUCCSS")
                .build());

        given(participationRepository.findByPostEntityPostIdAndUserId(anyLong(), anyString()))
            .willReturn(Optional.of(ParticipationEntity.builder().build()));

        given(participationRepository.findByPostEntityPostIdAndUserId(anyLong(), anyString()))
            .willReturn(Optional.of(ParticipationEntity.builder().build()));

        given(ratingRepository.existsByPostIdAndSenderUserIdAndReceiverUserId(anyLong(), anyLong(), anyLong()))
            .willReturn(false);

        //when
        ratingService.giveRating(giveRatingDto, postId, senderUserId);

        //then
        verify(ratingRepository, times(1)).save(captor.capture());
        assertEquals(1L, captor.getValue().getPostId());
        assertEquals(2L, captor.getValue().getSenderUserId());
        assertEquals(3L, captor.getValue().getReceiverUserId());
        assertEquals(5.0, captor.getValue().getScore());

    }

    @Test
    @DisplayName("평점 주기 실패 - 이미 평점을 주었음")
    void giveRatingFailBy이미_평점을_주었음() {

        //given
        long postId = 1L;
        long senderUserId = 2L;

        GiveRatingDto giveRatingDto = GiveRatingDto.builder()
            .score(7.0)
            .build();

        given(participationRepository.findByPostEntityPostIdAndUserId(anyLong(), anyString()))
            .willReturn(Optional.of(ParticipationEntity.builder().build()));

        given(participationRepository.findByPostEntityPostIdAndUserId(anyLong(), anyString()))
            .willReturn(Optional.of(ParticipationEntity.builder().build()));

        given(ratingRepository.existsByPostIdAndSenderUserIdAndReceiverUserId(anyLong(), anyLong(), anyLong()))
            .willReturn(true);

        //when
        BizException ex = assertThrows(BizException.class, () -> ratingService.giveRating(giveRatingDto, postId, senderUserId));

        //then
        assertEquals(RatingErrorCode.ALREADY_RATING, ex.getErrorCode());

    }

    @Test
    @DisplayName("평점 주기 실패 - 평점 범위가 다름")
    void giveRatingFailBy평점_범위가_다름() {

        //given
        long postId = 1L;
        long senderUserId = 2L;

        GiveRatingDto giveRatingDto = GiveRatingDto.builder()
            .score(7.0)
            .build();

        given(participationRepository.findByPostEntityPostIdAndUserId(anyLong(), anyString()))
            .willReturn(Optional.of(ParticipationEntity.builder().build()));

        given(participationRepository.findByPostEntityPostIdAndUserId(anyLong(), anyString()))
            .willReturn(Optional.of(ParticipationEntity.builder().build()));

        given(ratingRepository.existsByPostIdAndSenderUserIdAndReceiverUserId(anyLong(), anyLong(), anyLong()))
            .willReturn(false);


        //when
        BizException ex = assertThrows(BizException.class, () -> ratingService.giveRating(giveRatingDto, postId, senderUserId));

        //then
        assertEquals(RatingErrorCode.SCORE_OUT_OF_RANGE, ex.getErrorCode());

    }

    @Test
    @DisplayName("평점 주기 실패 - 평점 단위가 다름")
    void giveRatingFailBy평점_단위가_다름() {

        //given
        long postId = 1L;
        long senderUserId = 2L;

        GiveRatingDto giveRatingDto = GiveRatingDto.builder()
            .score(4.6)
            .build();

        given(participationRepository.findByPostEntityPostIdAndUserId(anyLong(), anyString()))
            .willReturn(Optional.of(ParticipationEntity.builder().build()));

        given(participationRepository.findByPostEntityPostIdAndUserId(anyLong(), anyString()))
            .willReturn(Optional.of(ParticipationEntity.builder().build()));

        given(ratingRepository.existsByPostIdAndSenderUserIdAndReceiverUserId(anyLong(), anyLong(), anyLong()))
            .willReturn(false);

        //when
        BizException ex = assertThrows(BizException.class, () -> ratingService.giveRating(giveRatingDto, postId, senderUserId));

        //then
        assertEquals(RatingErrorCode.SCORE_OUT_OF_UNIT, ex.getErrorCode());

    }
}
