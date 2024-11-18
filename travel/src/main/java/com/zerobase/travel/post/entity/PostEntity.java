package com.zerobase.travel.post.entity;

import com.zerobase.travel.communities.type.CustomException;
import com.zerobase.travel.communities.type.ErrorCode;
import com.zerobase.travel.post.type.Gender;
import com.zerobase.travel.post.type.LimitSex;
import com.zerobase.travel.post.type.LimitSmoke;
import com.zerobase.travel.post.type.MBTI;
import com.zerobase.travel.post.type.PostStatus;
import com.zerobase.travel.post.type.Smoking;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    // User와의 직접적인 매핑 관계 제거
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    private MBTI mbti;

    private String title;

    @Column(name = "travel_start_date")
    private LocalDate travelStartDate;

    @Column(name = "travel_end_date")
    private LocalDate travelEndDate;

    @Column(name = "max_participants")
    private Integer maxParticipants;

    @Enumerated(EnumType.STRING)
    @Column(name = "travel_country")
    private Country travelCountry;

    @Enumerated(EnumType.STRING)
    private Continent continent;

    private String region;

    @Column(name = "accommodation_fee")
    private Integer accommodationFee;

    @Column(name = "airplane_fee")
    private Integer airplaneFee;

    @Column(name = "other_expenses")
    private Integer otherExpenses;

    @Column(name = "limit_max_age")
    private Integer limitMaxAge;

    @Column(name = "limit_min_age")
    private Integer limitMinAge;

    @Enumerated(EnumType.STRING)
    @Column(name = "limit_sex")
    private LimitSex limitSex;

    @Enumerated(EnumType.STRING)
    @Column(name = "limit_smoke")
    private LimitSmoke limitSmoke;

    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PostStatus status;

    // Post와 Day 간의 연관관계 설정
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<DayEntity> days = new HashSet<>();



    public static boolean validateUserAge(PostEntity postEntity, int userAge) {
        if (postEntity.getLimitMinAge() > userAge || postEntity.getLimitMaxAge() < userAge) return false;
        return true;
    }

    public static boolean validateUserGEnder(PostEntity postEntity, Gender userGender) {
        if (postEntity.getLimitSex().equals(LimitSex.FEMALE)) {
            if(userGender.equals(Gender.FEMALE)){
                return false;
            }
        } else if (postEntity.getLimitSex().equals(LimitSex.MALE)) {
            if(userGender.equals(Gender.MALE)){
                return false;
            }
        }
        return true;

    }

    public static boolean validateSmoking(PostEntity postEntity, Smoking userSmoking) {

        if (postEntity.getLimitSmoke().equals(LimitSmoke.SMOKER)) {
            if (userSmoking.equals(Smoking.YES)) {
                    return false;
            }
        } else if (postEntity.getLimitSmoke().equals(LimitSmoke.NON_SMOKER)) {
            if (userSmoking.equals(Smoking.NO)) {
                return false;
            }
        }
        return true;
    }

    // 연관관계 편의 메서드 for DayEntity
    public void addDay(DayEntity day) {
        days.add(day);
        day.setPost(this);
    }

    public void removeDay(DayEntity day) {
        days.remove(day);
        day.setPost(null);
    }
}
