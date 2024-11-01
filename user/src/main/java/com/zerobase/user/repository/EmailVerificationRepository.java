package com.zerobase.user.repository;

import com.zerobase.user.entity.EmailVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerificationEntity, Long> {
    Optional<EmailVerificationEntity> findByEmail(String email);
}
