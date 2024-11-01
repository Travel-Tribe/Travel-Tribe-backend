package com.zerobase.user.repository;

import com.zerobase.user.entity.LangAbilityEntity;
import com.zerobase.user.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LangAbilityRepository extends JpaRepository<LangAbilityEntity, Long> {

    void deleteAllByProfileEntity(ProfileEntity profile);
}
