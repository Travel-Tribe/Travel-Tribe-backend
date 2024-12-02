package com.zerobase.travel.post.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "day_detail")
public class DayDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_detail_id")
    private Long dayDetailId;

    // Day와의 연관관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_id", nullable = false)
    private DayEntity day;

    private String title;

    private String description;

    @Column(name = "file_address")
    private String fileAddress;

    //순서를 위한 필드 추가
    @Column(name = "order_number")
    private Integer orderNumber;
}

