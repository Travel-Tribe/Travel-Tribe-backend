package com.zerobase.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerificationDTO {

    @Email(message = "USER-ERROR-VALID-00011")
    private String email;

    @NotBlank(message = "USER-ERROR-VALID-00019")
    private String code;
}
