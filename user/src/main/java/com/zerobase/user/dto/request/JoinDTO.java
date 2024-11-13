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

    @NotBlank(message = "USER-ERROR-VALID-00006")
    private String username;

    @NotBlank(message = "USER-ERROR-VALID-00007")
    private String nickname;

    @NotBlank(message = "USER-ERROR-VALID-00008")
    private String phone;

    @Size(min = 4, message = "USER-ERROR-VALID-00009")
    private String password;

    @Email(message = "USER-ERROR-VALID-00011")  // 오류 코드 문자열을 직접 사용
    private String email;

}