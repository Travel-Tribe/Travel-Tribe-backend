package com.zerobase.travel.post.dto.response;

import com.zerobase.travel.post.type.UserStatus;
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
}
