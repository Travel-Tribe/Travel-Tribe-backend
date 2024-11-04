package com.zerobase.travel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ParticipationEntity {

    @Id
    private Long participationId;
    @ManyToOne
    private PostEntity postEntity;
    private String userId;
    private ParticipationStatus participationStatus;



}
