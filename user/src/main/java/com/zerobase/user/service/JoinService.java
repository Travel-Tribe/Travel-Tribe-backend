package com.zerobase.user.service;

import com.zerobase.user.dto.request.JoinDTO;
import com.zerobase.user.dto.response.ServiceResult;
import com.zerobase.user.entity.UserEntity;
import com.zerobase.user.repository.UserRepository;
import com.zerobase.user.type.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ServiceResult joinProcess(JoinDTO joinDTO) {
        log.info("Processing registration for email: {}", joinDTO.getEmail());

        // 이메일 중복 체크
        if (userRepository.existsByEmail(joinDTO.getEmail())) {
            log.warn("Email already exists: {}", joinDTO.getEmail());
            throw new DataIntegrityViolationException("이미 존재하는 이메일입니다.");
        }

        // 사용자 엔티티 생성 및 저장
        userRepository.save(buildUser(joinDTO));
        log.info("User registration successful for email: {}", joinDTO.getEmail());

        // 회원가입 성공 시 201 Created 상태 코드와 메시지 반환
        return ServiceResult.success(HttpStatus.CREATED, "회원 가입에 성공했습니다!");
    }

    // UserEntity 빌더를 통한 엔티티 생성
    private UserEntity buildUser(JoinDTO joinDTO) {
        log.debug("Building user entity for email: {}", joinDTO.getEmail());
        return UserEntity.builder()
            .username(joinDTO.getUsername())
            .password(passwordEncoder.encode(joinDTO.getPassword()))
            .email(joinDTO.getEmail())
            .role(joinDTO.getRole())
            .phone(joinDTO.getPhone())
            .nickname(joinDTO.getNickname())
            .status(UserStatus.ACTIVE)
            .build();
    }
}