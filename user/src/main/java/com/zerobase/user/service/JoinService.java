package com.zerobase.user.service;

import com.zerobase.user.dto.request.JoinDTO;
import com.zerobase.user.dto.response.ResponseMessage;
import com.zerobase.user.entity.UserEntity;
import com.zerobase.user.repository.UserRepository;
import com.zerobase.user.type.Role;
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

    public void joinProcess(JoinDTO joinDTO) {
        log.info("Processing registration for email: {}", joinDTO.getEmail());

        // 이메일 중복 체크
        if (userRepository.existsByEmail(joinDTO.getEmail())) {
            log.warn("Email already exists: {}", joinDTO.getEmail());
            throw new DataIntegrityViolationException("이미 존재하는 이메일입니다.");
        }

        // 사용자 엔티티 생성 및 저장
        userRepository.save(buildUser(joinDTO));
        log.info("User registration successful for email: {}", joinDTO.getEmail());
    }

    // UserEntity 빌더를 통한 엔티티 생성
    private UserEntity buildUser(JoinDTO joinDTO) {
        log.debug("Building user entity for email: {}", joinDTO.getEmail());
        return UserEntity.builder()
            .username(joinDTO.getUsername())
            .password(passwordEncoder.encode(joinDTO.getPassword()))
            .email(joinDTO.getEmail())
            .role(Role.ROLE_USER)
            .phone(joinDTO.getPhone())
            .nickname(joinDTO.getNickname())
            .status(UserStatus.ACTIVE)
            .build();
    }

    public boolean checkDuplicate(String type, String query) {
        return switch (type.toLowerCase()) {
            case "email" -> userRepository.existsByEmail(query);
            case "nickname" -> userRepository.existsByNickname(query);
            case "phone" -> userRepository.existsByPhone(query);
            default -> throw new IllegalArgumentException("Invalid type: " + type);
        };
    }
}