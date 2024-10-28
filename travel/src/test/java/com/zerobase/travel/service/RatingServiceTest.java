package com.zerobase.travel.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.zerobase.travel.dto.request.GiveRatingDto;
import com.zerobase.travel.entity.RatingEntity;
import com.zerobase.travel.repository.RatingRepository;
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

    @InjectMocks
    private RatingService ratingService;

    @Test
    void giveRating() {

        //given
        long postId = 1L;
        long senderUserId = 2L;

        GiveRatingDto giveRatingDto = GiveRatingDto.builder()
            .score(5.0)
            .comment("좋아요")
            .receiverId(3L)
            .build();

        ArgumentCaptor<RatingEntity> captor = ArgumentCaptor.forClass(RatingEntity.class);

        //when
        ratingService.giveRating(giveRatingDto, postId, senderUserId);

        //then
        verify(ratingRepository, times(1)).save(captor.capture());
        assertEquals(1L, captor.getValue().getPostId());
        assertEquals(2L, captor.getValue().getSenderUserId());
        assertEquals(3L, captor.getValue().getReceiverUserId());
        assertEquals(5.0, captor.getValue().getScore());
        assertEquals("좋아요", captor.getValue().getComment());

    }
}
