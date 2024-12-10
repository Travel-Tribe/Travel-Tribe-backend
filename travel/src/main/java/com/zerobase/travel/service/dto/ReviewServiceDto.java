package com.zerobase.travel.service.dto;


import com.zerobase.travel.entity.ReviewEntity;
import com.zerobase.travel.entity.ReviewFileEntity;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

public class ReviewServiceDto {

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
        private List<ReviewFile> reviewFiles;

        public static Review fromEntity(ReviewEntity reviewEntity) {
            return Review.builder()
                .reviewId(reviewEntity.getId())
                .postId(reviewEntity.getPostId())
                .userId(reviewEntity.getUserId())
                .continent(reviewEntity.getContinent())
                .country(reviewEntity.getCountry())
                .region(reviewEntity.getRegion())
                .title(reviewEntity.getTitle())
                .contents(reviewEntity.getContents())
                .reviewFiles(
                    reviewEntity.getReviewFileList().stream()
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

        private Long id;
        private String fileAddress;

        public static ReviewFile fromEntity(ReviewFileEntity reviewFileEntity) {
            return ReviewFile.builder()
                .id(reviewFileEntity.getId())
                .fileAddress(reviewFileEntity.getFileAddress())
                .build();
        }
    }
}
