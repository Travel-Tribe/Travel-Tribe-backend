package com.zerobase.travel.dto.request;

import com.zerobase.travel.entity.ReviewEntity;
import com.zerobase.travel.entity.ReviewFileEntity;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ReviewRequestDto {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class CreateReview {

        private String continent;
        private String country;
        private String region;
        private String title;
        private String contents;
        private List<File> files;

        public ReviewEntity toEntity(Long id, long postId) {

            ReviewEntity reviewEntity = ReviewEntity.builder()
                .userId(id)
                .postId(postId)
                .continent(Continent.valueOf(continent))
                .country(Country.valueOf(country))
                .region(region)
                .title(title)
                .contents(contents)
                .build();

            reviewEntity.addReviewFiles(
                files.stream()
                    .map(File::toEntity)
                    .toList()
            );

            return reviewEntity;


        }

        @Getter
        @Setter
        @ToString
        public static class File {

            private String fileAddress;

            public ReviewFileEntity toEntity() {
                return ReviewFileEntity.builder()
                    .fileAddress(fileAddress)
                    .build();
            }
        }


    }


}
