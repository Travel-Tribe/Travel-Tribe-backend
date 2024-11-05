package com.zerobase.travel.service;

import com.zerobase.travel.dto.request.ReviewRequestDto.CreateReview;
import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.errorcode.ReviewErrorCode;
import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import com.zerobase.travel.post.entity.UserClient;
import com.zerobase.travel.repository.ReviewFileRepository;
import com.zerobase.travel.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewFileRepository reviewFileRepository;
    private final UserClient userClient;

    public void createReview(CreateReview request, String userEmail, long postId) {

        UserInfoResponseDTO userInfo = userClient.getUserInfoByEmail(userEmail);
        long userId = userInfo.getId();

        validationCreateReview(userId, postId);

        reviewRepository.save(request.toEntity(userId, postId));
    }

    private void validationCreateReview(long userId, long postId) {
        //후기를 이미 작성하였는지
        if (reviewRepository.existsByUserIdAndPostId(userId, postId)) {
            throw new BizException(ReviewErrorCode.ALREADY_WRITE_REVIEW);
        }
        
        //작성자가 여행을 갔다왔는지

    }

}
