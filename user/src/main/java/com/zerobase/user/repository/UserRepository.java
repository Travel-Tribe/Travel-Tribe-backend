package com.zerobase.user.repository;


import com.zerobase.user.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
    boolean existsByEmail(String email);  // 이메일 중복 여부 확인

    boolean existsByNickname(String query);

    boolean existsByPhone(String query);
}
