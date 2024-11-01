package com.zerobase.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.security.SecureRandom;
import org.springframework.stereotype.Component;

@Component
public class PasswordResetService {

    private final PasswordEncoder passwordEncoder;

    public PasswordResetService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String generateRandomPassword() {
        // 8자리의 랜덤 비밀번호 생성
        return generateRandomPassword(8);
    }

    public String encryptPassword(String password) {

        // 비밀번호 암호화
        return passwordEncoder.encode(password);
    }

    // 랜덤 비밀번호 생성
    private String generateRandomPassword(int length) {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialCharacters = "!@#$%^&*()-_+=<>?";

        String allowedChars = upperCaseLetters + lowerCaseLetters + digits + specialCharacters;
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(index));
        }
        return password.toString();
    }

}

