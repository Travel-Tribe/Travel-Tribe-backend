package com.zerobase.user.repository;

import com.zerobase.user.entity.ProfileEntity;
import com.zerobase.user.entity.VisitedCountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitedCountryRepository extends JpaRepository<VisitedCountryEntity, Long> {

    void deleteAllByProfileEntity(ProfileEntity profile);
}
