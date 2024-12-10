package com.zerobase.travel.dto.response;

import com.zerobase.travel.application.dto.ReviewFacadeDto;
import com.zerobase.travel.entity.ReviewEntity;
import com.zerobase.travel.entity.ReviewFileEntity;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

public class ReviewResponseDto {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class ReviewList {

        private Long reviewId;
        private Long postId;
        private Long userId;
        private String continent;
        private String country;
        private String region;
        private String title;
        private String contents;
        private String nickname;
        private String mbti;
        private String profileFileAddress;

        private List<ReviewFile> files;

        public static ReviewList fromDto(ReviewFacadeDto.Review entity) {
            return ReviewList.builder()
                .reviewId(entity.getReviewId())
                .postId(entity.getPostId())
                .userId(entity.getUserId())
                .continent(entity.getContinent().toString())
                .country(entity.getCountry().toString())
                .region(entity.getRegion())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .nickname(entity.getUser().getNickname())
                .mbti(entity.getUser().getMbti().toString())
                .profileFileAddress(entity.getUser().getFileAddress())
                .files(
                    entity.getReviewFiles().stream()
                        .map(ReviewFile::fromDto)
                        .toList()
                )
                .build();
        }

    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class Review {

        private Long reviewId;
        private Long postId;
        private Long userId;
        private String continent;
        private String country;
        private String region;
        private String title;
        private String contents;

        private List<ReviewFile> files;

        public static Review fromEntity(ReviewEntity entity) {
            return Review.builder()
                .reviewId(entity.getId())
                .postId(entity.getPostId())
                .userId(entity.getUserId())
                .continent(entity.getContinent().toString())
                .country(entity.getCountry().toString())
                .region(entity.getRegion())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .files(
                    entity.getReviewFileList().stream()
                        .map(ReviewFile::fromEntity)
                        .toList()
                )
                .build();
        }

    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class ReviewFile {

        private String fileAddress;

        public static ReviewFile fromEntity(ReviewFileEntity entity) {
            return ReviewFile.builder()
                .fileAddress(entity.getFileAddress())
                .build();
        }

        public static ReviewFile fromDto(ReviewFacadeDto.ReviewFile reviewFile) {
            return ReviewFile.builder()
                .fileAddress(reviewFile.getFileAddress())
                .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class ReviewPage {

        private List<ReviewList> reviews;
        private int pageNumber;
        private int pageSize;
        private long totalElements;
        private int totalPages;
        private boolean last;


        public static ReviewPage fromDto(Page<ReviewFacadeDto.Review> entityPage) {
            return ReviewPage.builder()
                .reviews(
                    entityPage.map(ReviewResponseDto.ReviewList::fromDto).toList()
                )
                .pageNumber(entityPage.getNumber())
                .pageSize(entityPage.getSize())
                .totalElements(entityPage.getTotalElements())
                .totalPages(entityPage.getTotalPages())
                .last(entityPage.isLast())
                .build();
        }

    }
}
