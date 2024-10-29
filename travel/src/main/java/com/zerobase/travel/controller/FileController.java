package com.zerobase.travel.controller;

import com.zerobase.travel.dto.response.FileResponseDto;
import com.zerobase.travel.dto.response.FileResponseDto.UploadFile;
import com.zerobase.travel.util.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

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

    @GetMapping("/download")
    public void fileUpload(
        HttpServletResponse response,
        @RequestParam String fileUrl
    ) {

        File file = new File(fileUrl);

        String downloadFileName = UriUtils.encode(fileUtil.getFileName(file), StandardCharsets.UTF_8);

        response.setContentType("application/download");
        response.setContentLength((int) file.length());
        response.setHeader("Content-disposition", "attachment;filename=\"" + downloadFileName + "\"");

        try (OutputStream os = response.getOutputStream(); FileInputStream fis = new FileInputStream(file)) {

            FileCopyUtils.copy(fis, os);

            //TODO 김용민 공통 에러처리 추가하기
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/preview")
    public ResponseEntity<byte[]> fileDownload(
        @RequestParam String fileUrl
    ) {
        File file = new File(fileUrl);
        String fileName = fileUtil.getFileName(file);

        MediaType mediaType = MediaTypeFactory.getMediaType(fileName).orElseThrow(RuntimeException::new);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", mediaType.toString());


        try {
            return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);

            //TODO 김용민 공통 에러처리 추가하기
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
