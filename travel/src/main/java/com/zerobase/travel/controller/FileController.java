package com.zerobase.travel.controller;

import com.zerobase.travel.dto.response.FileResponseDto;
import com.zerobase.travel.dto.response.FileResponseDto.UploadFile;
import com.zerobase.travel.util.FileUtil;
import java.io.File;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
public class FileController {

    private final FileUtil fileUtil;

    // 파일 저장은 추후 변경 가능 (현재는 DB에 저장하는 값이 파일 경로 그래도 나오 보안 위험)
    @PostMapping("/upload")
    public ResponseEntity<FileResponseDto.UploadFile> fileUpload(
        @RequestParam MultipartFile file
    ) {

        return ResponseEntity.ok(
            UploadFile.builder()
                .fileUrl(
                    fileUtil.saveFile(file).getAbsolutePath().replace("\\\\", "/")
                )
                .build()
        );
    }

}
