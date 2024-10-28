package com.zerobase.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessageHeader {

    private boolean result;
    private String resultCode;
    private String message;
    private int status;

}
