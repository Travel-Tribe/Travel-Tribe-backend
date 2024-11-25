package com.zerobase.travel.dto.response;

import com.zerobase.travel.entity.ReviewFileEntity;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ReviewViewResponseDto {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class GetReview {

        private long postId;
        private long reviewId;
        private long userId;
        private String continent;
        private String country;
        private String region;
        private String title;
        private String contents;
        private String nickname;
        private LocalDate travelStartDate;
        private LocalDate travelEndDate;
        private LocalDate createDate;

        private List<ReviewFile> files;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    public static class ReviewFile {

        private String fileAddress;

        public static ReviewViewResponseDto.ReviewFile fromEntity(ReviewFileEntity entity) {
            return ReviewViewResponseDto.ReviewFile.builder()
                .fileAddress(entity.getFileAddress())
                .build();
        }
    }

}
