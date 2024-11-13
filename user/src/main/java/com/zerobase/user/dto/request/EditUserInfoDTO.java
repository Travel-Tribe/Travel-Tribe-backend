package com.zerobase.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditUserInfoDTO {

    @NotBlank(message = "USER-ERROR-VALID-00007")
    private String nickname;

    @NotBlank(message = "USER-ERROR-VALID-00008")
    private String phone;

}
