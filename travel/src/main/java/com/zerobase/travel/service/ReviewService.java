package com.zerobase.travel.service;

import com.zerobase.travel.dto.request.ReviewRequestDto.CreateReview;
import com.zerobase.travel.dto.request.ReviewRequestDto.ModifyReview;
import com.zerobase.travel.dto.request.ReviewRequestDto.ModifyReview.File;
import com.zerobase.travel.dto.response.ReviewResponseDto;
import com.zerobase.travel.dto.response.ReviewResponseDto.ReviewPage;
import com.zerobase.travel.post.service.UserClientService;
import com.zerobase.travel.repository.specification.ReviewSearchDto;
import com.zerobase.travel.entity.ReviewEntity;
import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.errorcode.ReviewErrorCode;
import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import com.zerobase.travel.post.entity.UserClient;
import com.zerobase.travel.repository.ReviewFileRepository;
import com.zerobase.travel.repository.ReviewRepository;
import com.zerobase.travel.repository.specification.ReviewSpecification;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewFileRepository reviewFileRepository;
    private final UserClientService userClientService;

    public void createReview(CreateReview request, String userEmail, long postId) {

        UserInfoResponseDTO userInfo = userClientService.getUserInfo(userEmail);
        long userId = userInfo.getId();

        validationCreateReview(userId, postId);

        reviewRepository.save(request.toEntity(userId, postId));
    }

    @Transactional
    public void modifyReview(ModifyReview request, String userEmail, long postId, long reviewId) {

        UserInfoResponseDTO userInfo = userClientService.getUserInfo(userEmail);
        long userId = userInfo.getId();

        validationModifyReview(reviewId, userId, postId);

        ReviewEntity review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new BizException(ReviewErrorCode.NOT_FOUND_REVIEW));

        reviewFileRepository.deleteAll(review.getReviewFileList());
        review.removeReviewFiles();

        review.setTitle(request.getTitle());
        review.setContents(request.getContents());
        review.setContinent(Continent.valueOf(request.getContinent()));
        review.setCountry(Country.valueOf(request.getCountry()));
        review.setRegion(request.getRegion());
        review.addReviewFiles(
            request.getFiles().stream()
                .map(File::toEntity)
                .toList()
        );

        reviewRepository.save(review);
    }

    public void deleteReview(String userEmail, long postId, long reviewId) {

        UserInfoResponseDTO userInfo = userClientService.getUserInfo(userEmail);
        long userId = userInfo.getId();

        validationDeleteReview(reviewId, userId, postId);

        ReviewEntity review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new BizException(ReviewErrorCode.NOT_FOUND_REVIEW));

        reviewRepository.delete(review);
    }

    public ReviewResponseDto.Review getReview(long postId, long reviewId) {

        ReviewEntity review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new BizException(ReviewErrorCode.NOT_FOUND_REVIEW));

        validationGetReview(reviewId, postId);
        
        return ReviewResponseDto.Review.fromEntity(review);

    }

    public ReviewPage getReviews(ReviewSearchDto reviewSearchDto, PageRequest pageRequest) {

        return ReviewPage.fromPageEntity(
            reviewRepository.findAll(ReviewSpecification.filter(reviewSearchDto), pageRequest)
        );
    }

    private void validationGetReview(long reviewId, long postId) {
        //가져오려는 후기가 postid에 해당하는 후기인지
        if (!reviewRepository.existsByIdAndPostId(reviewId, postId)) {
            throw new BizException(ReviewErrorCode.THIS_REVIEW_NOT_IN_POST);
        }
    }

    private void validationCreateReview(long userId, long postId) {
        //후기를 이미 작성하였는지
        if (reviewRepository.existsByUserIdAndPostId(userId, postId)) {
            throw new BizException(ReviewErrorCode.ALREADY_WRITE_REVIEW);
        }

        //작성자가 여행을 갔다왔는지

    }

    private void validationModifyReview(long reviewId, long userId, long postId) {
        //내가쓴 후기가 맞는지
        if (!reviewRepository.existsByIdAndUserId(reviewId, userId)) {
            throw new BizException(ReviewErrorCode.NOT_MY_REVIEW);
        }

        //수정하려는 후기가 postid에 해당하는 후기인지
        if (!reviewRepository.existsByIdAndPostId(reviewId, postId)) {
            throw new BizException(ReviewErrorCode.THIS_REVIEW_NOT_IN_POST);
        }

    }

    private void validationDeleteReview(long reviewId, long userId, long postId) {
        //내가쓴 후기가 맞는지
        if (!reviewRepository.existsByIdAndUserId(reviewId, userId)) {
            throw new BizException(ReviewErrorCode.NOT_MY_REVIEW);
        }

        //삭제하려는 후기가 postid에 해당하는 후기인지
        if (!reviewRepository.existsByIdAndPostId(reviewId, postId)) {
            throw new BizException(ReviewErrorCode.THIS_REVIEW_NOT_IN_POST);
        }

    }
}
