package com.zerobase.user.dto.response;

import com.zerobase.user.type.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
@AllArgsConstructor
public class OtherUserInfoResponseDTO {

    private String username;
    private String nickname;
    private String email;
    private UserStatus status;
}
