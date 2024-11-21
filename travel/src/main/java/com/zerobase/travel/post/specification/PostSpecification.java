package com.zerobase.travel.post.specification;

import static com.zerobase.travel.post.type.PostStatus.*;

import com.zerobase.travel.post.dto.request.PostSearchCriteria;
import com.zerobase.travel.post.entity.DayDetailEntity;
import com.zerobase.travel.post.entity.DayEntity;
import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.post.constants.RepresentativeCountries;
import com.zerobase.travel.post.type.PostStatus;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class PostSpecification {

    public static Specification<PostEntity> getPosts(PostSearchCriteria criteria) {
        return (root, query, cb) -> {
            // 중복 결과를 방지하기 위해 DISTINCT 설정
            query.distinct(true);

            // 기본적으로 모두 만족
            var predicate = cb.conjunction();

            // 제목 필터링
            if (criteria.getTitle() != null && !criteria.getTitle().isEmpty()) {
                predicate = cb.and(predicate,
                    cb.like(cb.lower(root.get("title")),
                        "%" + criteria.getTitle().toLowerCase() + "%"));
            }

            // content 파라미터를 description 필터링으로 변경
            if (criteria.getContent() != null && !criteria.getContent().isEmpty()) {
                // PostEntity -> DayEntity -> DayDetailEntity로 조인
                Join<PostEntity, DayEntity> daysJoin = root.join("days", JoinType.LEFT);
                Join<DayEntity, DayDetailEntity> dayDetailsJoin = daysJoin.join("dayDetails", JoinType.LEFT);

                // description 필드에 대한 필터링
                predicate = cb.and(predicate,
                    cb.like(cb.lower(dayDetailsJoin.get("description")),
                        "%" + criteria.getContent().toLowerCase() + "%"));
            }

            // 대륙 필터링 (Enum 직접 비교)
            if (criteria.getContinent() != null) {
                predicate = cb.and(predicate,
                    cb.equal(root.get("continent"), criteria.getContinent()));
            }

            // MBTI 필터링 (Enum 직접 비교)
            if (criteria.getMbti() != null) {
                predicate = cb.and(predicate,
                    cb.equal(root.get("mbti"), criteria.getMbti()));
            }

            // '기타' 국가 필터링
            if (Boolean.TRUE.equals(criteria.getOthers())) {
                predicate = cb.and(predicate,
                    cb.not(root.get("travelCountry").in(RepresentativeCountries.ALL_REPRESENTATIVE_COUNTRIES)));
            } else if (criteria.getCountry() != null) {
                // 대표 국가로 필터링
                predicate = cb.and(predicate,
                    cb.equal(root.get("travelCountry"), criteria.getCountry()));
            }

            // 상태가 RECRUITMENT_COMPLETED 또는 DELETED가 아닌 게시글만 조회
            predicate = cb.and(predicate,
                cb.not(root.get("status").in(
                    RECRUITMENT_COMPLETED,
                    PAYMENT_PENDING,
                    DELETED
                ))
            );

            return predicate;
        };
    }
}
