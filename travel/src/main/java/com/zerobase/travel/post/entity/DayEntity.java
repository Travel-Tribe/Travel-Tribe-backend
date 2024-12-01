package com.zerobase.travel.post.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    @OrderColumn(name = "order_number")
    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<DayDetailEntity> dayDetails = new ArrayList<>();

    // Day와 ItineraryVisit 간의 연관관계 설정
    @OrderColumn(name = "order_number")
    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ItineraryVisitEntity> itineraryVisits = new ArrayList<>();

    // 연관관계 편의 메서드 for DayDetailEntity
    public void addDayDetail(DayDetailEntity dayDetail) {
        if (!dayDetails.contains(dayDetail)) {
            dayDetails.add(dayDetail);
            dayDetail.setDay(this);
        }
    }


    public void removeDayDetail(DayDetailEntity dayDetail) {
        dayDetails.remove(dayDetail);
        dayDetail.setDay(null);
    }

    // 연관관계 편의 메서드 for ItineraryVisitEntity
    public void addItineraryVisit(ItineraryVisitEntity itineraryVisit) {
        if (!itineraryVisits.contains(itineraryVisit)) {
            itineraryVisits.add(itineraryVisit);
            itineraryVisit.setDay(this);
        }
    }

    public void removeItineraryVisit(ItineraryVisitEntity itineraryVisit) {
        itineraryVisits.remove(itineraryVisit);
        itineraryVisit.setDay(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DayEntity)) return false;
        DayEntity that = (DayEntity) o;
        return Objects.equals(dayId, that.dayId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayId);
    }
}
