package com.zerobase.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinDTO {

    @NotBlank(message = "E01006")
    private String username;

    @NotBlank(message = "E01007")
    private String nickname;

    @NotBlank(message = "E01008")
    private String phone;

    @Size(min = 4, message = "E01009")
    private String password;

    @Email(message = "E01011")  // 오류 코드 문자열을 직접 사용
    private String email;

}