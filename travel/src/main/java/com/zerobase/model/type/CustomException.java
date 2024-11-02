package com.zerobase.model.type;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    ErrorCode errorCode;


}
