package com.zerobase.communities.entity;

import com.zerobase.typeCommon.Continent;
import com.zerobase.typeCommon.Country;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CommunityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communityId;
    @Column(nullable = false)
    private Long userId;
    @Enumerated(EnumType.STRING)
    private Continent continent;
    @Enumerated(EnumType.STRING)
    private Country country;
    private String region;
    private String title;
    private String content;





}

