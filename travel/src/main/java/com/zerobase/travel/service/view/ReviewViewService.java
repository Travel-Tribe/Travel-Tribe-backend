package com.zerobase.travel.service.view;

import com.zerobase.travel.dto.response.ReviewViewResponseDto;
import com.zerobase.travel.dto.response.ReviewViewResponseDto.GetReview;
import com.zerobase.travel.entity.ReviewEntity;
import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.errorcode.BasicErrorCode;
import com.zerobase.travel.exception.errorcode.ReviewErrorCode;
import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.post.entity.UserClient;
import com.zerobase.travel.post.repository.PostRepository;
import com.zerobase.travel.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewViewService {

    private final ReviewRepository reviewRepository;
    private final PostRepository postRepository;
    private final UserClient userClient;

    public GetReview getReview(long postId, long reviewId) {
        ReviewEntity reviewEntity = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new BizException(ReviewErrorCode.NOT_FOUND_REVIEW));

        PostEntity postEntity = postRepository.findById(postId)
            .orElseThrow(() -> new BizException(BasicErrorCode.POST_NOT_FOUND_ERROR));

        UserInfoResponseDTO userInfo = userClient.searchUserInfo("userId", String.valueOf(postEntity.getUserId())).getData();

        return GetReview.builder()
            .postId(reviewEntity.getPostId())
            .reviewId(reviewEntity.getId())
            .userId(reviewEntity.getUserId())
            .continent(reviewEntity.getContinent().toString())
            .country(reviewEntity.getCountry().toString())
            .region(reviewEntity.getRegion())
            .title(reviewEntity.getTitle())
            .contents(reviewEntity.getContents())
            .nickname(userInfo.getNickname())
            .travelStartDate(postEntity.getTravelStartDate())
            .travelEndDate(postEntity.getTravelStartDate())
            .createDate(reviewEntity.getCreateDate().toLocalDate())
            .files(
                reviewEntity.getReviewFileList().stream()
                    .map(ReviewViewResponseDto.ReviewFile::fromEntity)
                    .toList()
            )
            .build();


    }
}
