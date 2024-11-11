package com.zerobase.user.dto.response;

import com.zerobase.user.type.Gender;
import com.zerobase.user.type.MBTI;
import com.zerobase.user.type.Smoking;
import com.zerobase.user.type.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
@AllArgsConstructor
public class UserInfoResponseDTO {

    private Long id;
    private String username;
    private String nickname;
    private String phone;
    private String email;
    private UserStatus status;
    private MBTI mbti;
    private Smoking smoking;
    private String introduction;
    private Gender gender;
    private LocalDate birth;
    private Double ratingAvg;
}
