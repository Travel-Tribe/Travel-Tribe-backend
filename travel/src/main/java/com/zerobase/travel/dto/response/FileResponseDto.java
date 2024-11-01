package com.zerobase.travel.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class FileResponseDto {

    @Getter
    @Setter
    @Builder
    @ToString
    public static class UploadFile {

        private final String fileUrl;
    }

}
