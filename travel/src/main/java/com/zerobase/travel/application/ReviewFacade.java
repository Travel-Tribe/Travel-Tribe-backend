package com.zerobase.travel.application;

import com.zerobase.travel.application.dto.ReviewFacadeDto;
import com.zerobase.travel.application.dto.ReviewFacadeDto.Review;
import com.zerobase.travel.post.entity.UserClient;
import com.zerobase.travel.repository.specification.ReviewSearchDto;
import com.zerobase.travel.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewFacade {

    private final ReviewService reviewService;
    private final UserClient userClient;

    public Page<Review> getReviews(ReviewSearchDto reviewSearchDto, PageRequest pageRequest) {
        return reviewService.getReviews(reviewSearchDto, pageRequest)
            .map(review -> ReviewFacadeDto.Review.fromDto(
                review,
                userClient.searchUserInfo("userId", review.getUserId().toString()).getData()
            ));

    }

}
