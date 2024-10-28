package com.zerobase.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {

    private ResponseMessageHeader header;
    private Object body;

    // 실패 응답 생성 (동적으로 상태 코드 포함)
    public static ResponseMessage fail(HttpStatus status, String message, Object data) {
        return ResponseMessage.builder()
            .header(ResponseMessageHeader.builder()
                .result(false)
                .resultCode(status.getReasonPhrase())  // 상태 코드 설명 (예: "Bad Request")
                .message(message)
                .status(status.value())  // 상태 코드 (예: 400, 404)
                .build())
            .body(data)
            .build();
    }

    // 성공 응답 생성 (동적으로 상태 코드 포함)
    public static ResponseMessage success(HttpStatus status, String message, Object data) {
        return ResponseMessage.builder()
            .header(ResponseMessageHeader.builder()
                .result(true)
                .resultCode(status.getReasonPhrase())  // 상태 코드 설명 (예: "OK")
                .message(message)
                .status(status.value())  // 상태 코드 (예: 200)
                .build())
            .body(data)
            .build();
    }
}

