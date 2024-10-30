package com.zerobase.user.dto.request;

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
public class EditUserPasswordDTO {

    @Size(min = 4, message = "E01009")
    private String password;

    @Size(min = 4, message = "E01010")
    private String newPassword;

}