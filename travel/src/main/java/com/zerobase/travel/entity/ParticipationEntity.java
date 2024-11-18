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
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;


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
    @NotNull
    private ParticipationStatus participationStatus;
    @Enumerated(EnumType.STRING)
    @NotNull
    private DepositStatus depositStatus;
    @Enumerated(EnumType.STRING)
    @NotNull
    private RatingStatus ratingStatus;

    LocalDate depositReturnDate;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // participation 의 상황에 따른 status 모음
    @Transient
    public static List<Enum<?>> beforePayStatuses = List.of(ParticipationStatus.JOIN_READY,DepositStatus.UNPAID,RatingStatus.NOT_RATED);
    @Transient
    public static List<Enum<?>> afterPaySuccessStatuses = List.of(ParticipationStatus.JOIN,DepositStatus.PAID,RatingStatus.NOT_RATED);
    @Transient
    public static List<Enum<?>> afterPayFailStatuses = List.of(ParticipationStatus.JOIN_FAILED,DepositStatus.UNPAID,RatingStatus.NOT_RATED);
    @Transient
    public static List<Enum<?>> afterVotingStatuses = List.of(ParticipationStatus.JOIN_CANCEL,DepositStatus.RETURNED,RatingStatus.NOT_RATED);
    @Transient
    public static List<Enum<?>> afterCancelStatuses = List.of(ParticipationStatus.JOIN_CANCEL,DepositStatus.FORFEITED,RatingStatus.NOT_RATED);
    @Transient
    public static List<Enum<?>> afterTravelFinishStatusesUnRated = List.of(ParticipationStatus.TRAVEL_FINISHED,DepositStatus.PAID,RatingStatus.NOT_RATED);

    @Transient
    public static List<Enum<?>> afterTravelFinishStatusesRated = List.of(ParticipationStatus.TRAVEL_FINISHED,DepositStatus.PAID,RatingStatus.RATED);


    @Transient
    public static List<Enum<?>> afterRatingStatuses = List.of(ParticipationStatus.TRAVEL_FINISHED,DepositStatus.PAID,RatingStatus.RATED);
    @Transient
    public static List<Enum<?>> afterTravelFinishStatusesExcludeRating = List.of(ParticipationStatus.TRAVEL_FINISHED,DepositStatus.PAID);
    @Transient
    public static List<Enum<?>> afterDepositReturnedExcludeRating = List.of(ParticipationStatus.TRAVEL_FINISHED,DepositStatus.PAID);




    public boolean hasStatus(Enum<?> status) {
        if (status instanceof ParticipationStatus) {
            return this.participationStatus == status;
        } else if (status instanceof DepositStatus) {
            return this.depositStatus == status;
        } else if (status instanceof RatingStatus) {
            return this.ratingStatus == status;
        }
        return false;
    }

    public void updateStatus(Enum<?> status) {
        if (status instanceof ParticipationStatus) {
            this.participationStatus = (ParticipationStatus) status;
        } else if (status instanceof DepositStatus) {
            this.depositStatus = (DepositStatus) status;
        } else if (status instanceof RatingStatus) {
            this.ratingStatus = (RatingStatus) status;
        }
    }



}