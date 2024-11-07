package com.zerobase.travel.entity;

import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "review")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private Continent continent;

    @Enumerated(EnumType.STRING)
    private Country country;

    private String region;

    private String title;

    private String contents;

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewFileEntity> reviewFileList = new ArrayList<>();

    public void addReviewFile(ReviewFileEntity reviewFileEntity) {
        reviewFileEntity.setReview(this);
        reviewFileList.add(reviewFileEntity);
    }

    public void addReviewFiles(List<ReviewFileEntity> reviewFileEntitys) {

        for (ReviewFileEntity reviewFileEntity : reviewFileEntitys) {
            addReviewFile(reviewFileEntity);
        }
    }

    public void removeReviewFiles() {

        reviewFileList = new ArrayList<>();
    }
}
