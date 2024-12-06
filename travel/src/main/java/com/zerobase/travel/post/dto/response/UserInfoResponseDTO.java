package com.zerobase.travel.post.dto.response;

import com.zerobase.travel.post.type.Gender;
import com.zerobase.travel.post.type.MBTI;
import com.zerobase.travel.post.type.Smoking;
import com.zerobase.travel.post.type.UserStatus;
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
    private String fileAddress;
    private Double ratingAvg;
}
