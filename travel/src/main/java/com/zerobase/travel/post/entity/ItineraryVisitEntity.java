package com.zerobase.travel.post.entity;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;
import lombok.*;

@Entity
@Table(name = "itinerary_visit")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItineraryVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itinerary_visit_id")
    private Long itineraryVisitId;

    // Day와의 연관관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_id", nullable = false)
    private DayEntity day;

    @Column(columnDefinition = "POINT")
    private Point point; // 위치 정보

    @Column(name = "order_number")
    private Integer orderNumber;
}
