package com.zerobase.travel.dto.response;

import com.zerobase.travel.entity.ReviewEntity;
import com.zerobase.travel.entity.ReviewFileEntity;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ReviewResponseDto {

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
    }


}
