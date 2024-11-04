package com.zerobase.travel.entity;

import com.zerobase.travel.post.entity.PostEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ParticipationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participationId;
    @ManyToOne
    private PostEntity postEntity;
    private String userId;
    @Enumerated(EnumType.STRING)
    private ParticipationStatus participationStatus;



}
