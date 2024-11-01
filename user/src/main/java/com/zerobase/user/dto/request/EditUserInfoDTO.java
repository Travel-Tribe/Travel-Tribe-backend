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

    @NotBlank(message = "E01007")
    private String nickname;

    @NotBlank(message = "E01008")
    private String phone;

}
