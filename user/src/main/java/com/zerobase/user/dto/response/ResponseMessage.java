package com.zerobase.user.dto.response;

import static com.zerobase.user.dto.response.ResponseMessage.Result.FAIL;
import static com.zerobase.user.dto.response.ResponseMessage.Result.SUCCESS;

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
        return new ResponseMessage<T>(SUCCESS.toString(), null, data);
    }

    public static ResponseMessage<Void> fail(ErrorCode errorCode) {
        return ResponseMessage.<Void>builder()
            .result(FAIL.toString())
            .errors(List.of(new Errors(errorCode)))
            .build();
    }

    public static ResponseMessage<Object> fail(ErrorCode errorCode, Object data) {
        return ResponseMessage.builder()
            .result(FAIL.toString())
            .data(data)
            .errors(List.of(new Errors(errorCode)))
            .build();
    }

    public static <T> ResponseMessage<T> fail(List<Errors> errors) {
        return ResponseMessage.<T>builder()
            .result(FAIL.toString())
            .errors(errors)
            .build();
    }

    public enum Result {
        SUCCESS, FAIL
    }
}
