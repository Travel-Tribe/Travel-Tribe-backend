package com.zerobase.travel.post.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "day")
public class DayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_id")
    private Long dayId;

    // Post와의 연관관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    // Day와 DayDetail 간의 연관관계 설정
    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<DayDetailEntity> dayDetails = new HashSet<>();

    // Day와 ItineraryVisit 간의 연관관계 설정
    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<ItineraryVisitEntity> itineraryVisits = new HashSet<>();

    // 연관관계 편의 메서드 for DayDetailEntity
    public void addDayDetail(DayDetailEntity dayDetail) {
        dayDetails.add(dayDetail);
        dayDetail.setDay(this);
    }

    public void removeDayDetail(DayDetailEntity dayDetail) {
        dayDetails.remove(dayDetail);
        dayDetail.setDay(null);
    }

    // 연관관계 편의 메서드 for ItineraryVisitEntity
    public void addItineraryVisit(ItineraryVisitEntity itineraryVisit) {
        itineraryVisits.add(itineraryVisit);
        itineraryVisit.setDay(this);
    }

    public void removeItineraryVisit(ItineraryVisitEntity itineraryVisit) {
        itineraryVisits.remove(itineraryVisit);
        itineraryVisit.setDay(null);
    }
}
