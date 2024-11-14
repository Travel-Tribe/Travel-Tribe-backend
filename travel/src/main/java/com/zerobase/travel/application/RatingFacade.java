package com.zerobase.travel.application;

import com.zerobase.travel.dto.request.GiveRatingDto;
import com.zerobase.travel.service.ParticipationManagementService;
import com.zerobase.travel.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingFacade {

    private final ParticipationManagementService participationManagementService;
    private final RatingService ratingService;

    public void giveRating(GiveRatingDto giveRatingDto, long postId, long senderUserId) {
        ratingService.giveRating(giveRatingDto, postId, senderUserId);
        participationManagementService.giveRatingParticipation(postId, String.valueOf(senderUserId));
    }

}
