package com.zerobase.travel.application.dto;

import com.zerobase.travel.post.dto.response.UserInfoResponseDTO;
import com.zerobase.travel.post.type.Gender;
import com.zerobase.travel.post.type.MBTI;
import com.zerobase.travel.post.type.Smoking;
import com.zerobase.travel.post.type.UserStatus;
import com.zerobase.travel.service.dto.ReviewServiceDto;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ReviewFacadeDto {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class Review {
        private Long reviewId;
        private Long postId;
        private Long userId;
        private Continent continent;
        private Country country;
        private String region;
        private String title;
        private String contents;
        private List<ReviewFacadeDto.ReviewFile> reviewFiles;

        private User user;

        public static Review fromDto(ReviewServiceDto.Review review, UserInfoResponseDTO dto) {
            return Review.builder()
                .reviewId(review.getReviewId())
                .postId(review.getPostId())
                .userId(review.getUserId())
                .continent(review.getContinent())
                .country(review.getCountry())
                .region(review.getRegion())
                .title(review.getTitle())
                .contents(review.getContents())
                .reviewFiles(
                    review.getReviewFiles().stream()
                        .map(ReviewFile::fromDto)
                        .toList()
                )
                .user(User.fromDto(dto))
                .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class ReviewFile {

        private Long id;
        private String fileAddress;

        public static ReviewFacadeDto.ReviewFile fromDto(ReviewServiceDto.ReviewFile reviewFile) {
            return ReviewFacadeDto.ReviewFile.builder()
                .id(reviewFile.getId())
                .fileAddress(reviewFile.getFileAddress())
                .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class User {
        private Long id;
        private String username;
        private String nickname;
        private String phone;
        private String email;
        private UserStatus status;
        private MBTI mbti;
        private Smoking smoking;
        private String introduction;
        private Gender gender;
        private LocalDate birth;
        private String fileAddress;
        private Double ratingAvg;

        public static User fromDto(UserInfoResponseDTO dto) {
            return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .nickname(dto.getNickname())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .status(dto.getStatus())
                .mbti(dto.getMbti())
                .smoking(dto.getSmoking())
                .introduction(dto.getIntroduction())
                .gender(dto.getGender())
                .birth(dto.getBirth())
                .fileAddress(dto.getFileAddress())
                .ratingAvg(dto.getRatingAvg())
                .build();
        }
    }
}
