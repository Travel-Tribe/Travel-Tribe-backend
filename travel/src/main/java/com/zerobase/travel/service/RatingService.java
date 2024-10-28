package com.zerobase.travel.service;

import com.zerobase.travel.dto.request.GiveRatingDto;
import com.zerobase.travel.entity.RatingEntity;
import com.zerobase.travel.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final static double MIN_SCORE = 0.0;
    private final static double MAX_SCORE = 0.0;
    private final static double SCORE_UNIT = 0.5;

    private final RatingRepository ratingRepository;

    public void giveRating(GiveRatingDto giveRatingDto, long postId,
        long senderUserId) {

        validationRegisterRating(giveRatingDto.getScore());

        RatingEntity rating = RatingEntity.builder()
            .postId(postId)
            .senderUserId(senderUserId)
            .receiverUserId(giveRatingDto.getReceiverId())
            .score(giveRatingDto.getScore())
            .comment(giveRatingDto.getComment())
            .build();

        ratingRepository.save(rating);

        //TODO 김용민 받은사람 프로필에 반영해주기
        // 평점반영 api 필요
    }

    //TODO 김용민 validationRegisterRating 작성하기
    private void validationRegisterRating(double score) {
        //점수 준 사람, 받은 사람이 해당 여행에 참여 하였는지
        // join 테이블 생성시 작성

        //score가 0 ~ 5점 사이이고 0.5점 단위 인지
        if (score < MIN_SCORE || score > MAX_SCORE) {

        }

        if (score % SCORE_UNIT != 0) {

        }

        //여행이 종료 되었는지
    }

}
