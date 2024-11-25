package com.zerobase.travel.service;

import com.zerobase.travel.api.UserApi;
import com.zerobase.travel.dto.request.GiveRatingDto;
import com.zerobase.travel.entity.RatingEntity;
import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.errorcode.RatingErrorCode;
import com.zerobase.travel.exception.errorcode.VoteErrorCode;
import com.zerobase.travel.repository.ParticipationRepository;
import com.zerobase.travel.repository.RatingRepository;
import com.zerobase.travel.type.ParticipationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final static double MIN_SCORE = 0.0;
    private final static double MAX_SCORE = 5.0;
    private final static double SCORE_UNIT = 0.5;

    private final RatingRepository ratingRepository;
    private final ParticipationRepository participationRepository;
    private final UserApi userApi;

    @Transactional
    public void giveRating(GiveRatingDto giveRatingDto, long postId, long senderUserId) {

        validationRegisterRating(postId, senderUserId, giveRatingDto.getReceiverId(), giveRatingDto.getScore());

        RatingEntity rating = RatingEntity.builder()
            .postId(postId)
            .senderUserId(senderUserId)
            .receiverUserId(giveRatingDto.getReceiverId())
            .score(giveRatingDto.getScore())
            .build();

        ratingRepository.save(rating);

        Double avgReceiverRating = ratingRepository.getAvgReceiverRating(giveRatingDto.getReceiverId());
        double avrRating = Math.ceil(avgReceiverRating * 10) / 10.0;
        userApi.updateUserRating(giveRatingDto.getReceiverId(), avrRating);

    }

    private void validationRegisterRating(long postId, long senderUserId, long receiverUserId, double score) {
        //받은 사람이 해당 여행에 참여 하였는지
        if (participationRepository.findByPostEntityPostIdAndUserId(postId, String.valueOf(receiverUserId)).isEmpty()) {
            throw new BizException(VoteErrorCode.UNJOIN_TRAVEL);
        }

        //점수 준 사람이 해당 여행에 참여 하였는지
        if (participationRepository.findByPostEntityPostIdAndUserId(postId, String.valueOf(senderUserId)).isEmpty()) {
            throw new BizException(VoteErrorCode.UNJOIN_TRAVEL);
        }

        //이미 해당 사람에게 점수를 주었는지
        if (ratingRepository.existsByPostIdAndSenderUserIdAndReceiverUserId(postId, senderUserId, receiverUserId)) {
            throw new BizException(RatingErrorCode.ALREADY_RATING);
        }

        //score가 0 ~ 5점 사이이고 0.5점 단위 인지
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new BizException(RatingErrorCode.SCORE_OUT_OF_RANGE);
        }

        if (score % SCORE_UNIT != 0) {
            throw new BizException(RatingErrorCode.SCORE_OUT_OF_UNIT);
        }

        //주는 사람 여행 참여상태가 travel_finish 일때 가능
        if (!ParticipationStatus.TRAVEL_FINISHED
            .equals(participationRepository.findByPostEntityPostIdAndUserId(postId, String.valueOf(senderUserId)).get().getParticipationStatus())
        ) {
            throw new BizException(RatingErrorCode.NOT_YET_GIVE_RATING);
        }

        //받는 사람 여행 참여상태가 travel_finish 일때 가능
        if (!ParticipationStatus.TRAVEL_FINISHED
            .equals(participationRepository.findByPostEntityPostIdAndUserId(postId, String.valueOf(receiverUserId)).get().getParticipationStatus())
        ) {
            throw new BizException(RatingErrorCode.NOT_YET_GIVE_RATING);
        }
    }

}
