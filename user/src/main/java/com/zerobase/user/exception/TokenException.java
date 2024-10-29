package com.zerobase.user.exception;

import com.zerobase.user.dto.response.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TokenException extends BaseException {

    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public TokenException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
        this.httpStatus = HttpStatus.UNAUTHORIZED;
    }

    public TokenException(ErrorCode errorCode, HttpStatus httpStatus) {
        super(errorCode);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}

