package com.zerobase.user.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginSuccessDTO {

    private Long Id;
    private Boolean profileCheck;
}
