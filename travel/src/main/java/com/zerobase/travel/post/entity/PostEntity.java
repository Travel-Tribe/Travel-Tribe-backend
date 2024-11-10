package com.zerobase.travel.post.entity;

import com.zerobase.travel.post.type.LimitSex;
import com.zerobase.travel.post.type.LimitSmoke;
import com.zerobase.travel.post.type.MBTI;
import com.zerobase.travel.post.type.PostStatus;
import com.zerobase.travel.typeCommon.Continent;
import com.zerobase.travel.typeCommon.Country;
import jakarta.persistence.*;
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

    @Column(name = "transportation_fee")
    private Integer transportationFee;

    @Column(name = "airplane_fee")
    private Integer airplaneFee;

    @Column(name = "food_fee")
    private Integer foodFee;

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

    @Column(name = "preference_type")
    private String preferenceType;

    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    // Post와 Day 간의 연관관계 설정
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<DayEntity> days = new HashSet<>();

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
