package com.zerobase.user.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage<T> {

    private String result;
    private List<Errors> errors;
    private T data;

    public static <T> ResponseMessage<T> success() {
        return success(null);
    }

    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage<T>(Result.SUCCESS.toString(), null, data);
    }

    public static ResponseMessage fail(ErrorCode errorCode) {
        return ResponseMessage.builder()
            .result(Result.FAIL.toString())
            .errors(List.of(new Errors(errorCode)))
            .build();
    }

    public static ResponseMessage fail(ErrorCode errorCode, Object data) {
        return ResponseMessage.builder()
            .result(Result.FAIL.toString())
            .data(data)
            .errors(List.of(new Errors(errorCode)))
            .build();
    }

    public static ResponseMessage fail(List<Errors> errors) {
        return ResponseMessage.builder()
            .result(Result.FAIL.toString())
            .errors(errors)
            .build();
    }


    public enum Result {
        SUCCESS, FAIL
    }
}
