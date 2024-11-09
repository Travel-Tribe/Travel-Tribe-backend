package com.zerobase.travel.repository.specification;

import com.zerobase.travel.entity.ReviewEntity;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import org.springframework.data.jpa.domain.Specification;

public class ReviewSpecification {

    public static Specification<ReviewEntity> filter(ReviewSearchDto dto) {
        Specification<ReviewEntity> specification = initialize();

        return specification
            .and(likeTitle(dto.getTitle()))
            .and(likeContent(dto.getContent()))
            .and(equalContinent(dto.getContinent()))
            .and(equalCountry(dto.getCountry()))
            .and(equalUserId(dto.getUserId()))
            .and(orderByIdDesc());
    }

    private static Specification<ReviewEntity> initialize() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    private static Specification<ReviewEntity> likeTitle(final String title) {

        if (title == null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }

        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    private static Specification<ReviewEntity> likeContent(final String content) {

        if (content == null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }

        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("contents"), "%" + content + "%");
    }

    private static Specification<ReviewEntity> equalContinent(final String continent) {

        if (continent == null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("continent"), Continent.valueOf(continent));
    }

    private static Specification<ReviewEntity> equalCountry(final String country) {

        if (country == null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("country"), Country.valueOf(country));
    }

    private static Specification<ReviewEntity> equalUserId(final Long userId) {

        if (userId == null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), userId);
    }

    private static Specification<ReviewEntity> orderByIdDesc() {
        return (root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.desc(root.get("id")));
            return criteriaBuilder.conjunction();
        };
    }
}
