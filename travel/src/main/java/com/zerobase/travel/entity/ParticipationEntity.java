package com.zerobase.travel.entity;

import com.zerobase.travel.post.entity.PostEntity;
import com.zerobase.travel.type.DepositStatus;
import com.zerobase.travel.type.ParticipationStatus;
import com.zerobase.travel.type.RatingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participationId;
    @ManyToOne
    private PostEntity postEntity;
    private String userId;

    /*
    ParticipationEntity의 상태에 대해서 3가지로 나눈이유
    1. 보증금에 대해서 관리의 주체는 participation이 되어야한다고 생각함.
    이력에 대한 데이터는 결제이력이나 레이팅이력,참여이력으로 기록이 되지만 최종적으로 보증금의 상태에 대해서 마스터로 관리할만한 객체가 참여뿐임

    2. 마케팅 관점에서도 유의미한 데이터단위로 나누어야하기때문에 3가지 상태는 분리되어야생각하였음.
    통계를 낸다면 평가를 한사람 안한사람, 중도참여 중단한사람의 비율 등을 조사할것이라 생각됨
     */

    @Enumerated(EnumType.STRING)
    private ParticipationStatus participationStatus;
    @Enumerated(EnumType.STRING)
    private DepositStatus depositStatus;
    @Enumerated(EnumType.STRING)
    private RatingStatus ratingStatus;

    LocalDateTime depositReturnDate;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // A list to hold each status without persisting it to the database
    @Transient
    private List<Enum<?>> statuses;

    // You can initialize this list in the constructor or a method
    public void initializeStatuses() {
        this.statuses = List.of(participationStatus, depositStatus, ratingStatus);
    }




}