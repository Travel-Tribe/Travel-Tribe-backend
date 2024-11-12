package com.zerobase.travel.util;

import com.zerobase.travel.exception.BizException;
import com.zerobase.travel.exception.errorcode.BasicErrorCode;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {

    public static final String FILE_PATH_DIVISION = "____";

    @Value("${file.upload.path}")
    private String fileUploadPath;

    public void createDirectories(String path) {

        try {

            if (!isExistDirectories(path)) {
                Files.createDirectories(Paths.get(path));
            }

        } catch (IOException e) {
            throw new BizException(BasicErrorCode.INTERNAL_SERVER_ERROR);
        }

    }

    public boolean isExistDirectories(String path) {

        return Files.exists(Paths.get(path));
    }

    public File saveFile(MultipartFile requestFile) {

        try {

            createDirectories(fileUploadPath);

            String originalFileName = requestFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();

            String savedFileName = uuid + FILE_PATH_DIVISION + originalFileName;
            String fileUrl = fileUploadPath + "/" + savedFileName;

            File file = new File(fileUrl);

            requestFile.transferTo(file);

            return file;

        } catch (IOException e) {
            throw new BizException(BasicErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public String getFileName(File file) {
        String fileName = file.getName();
        int fileDivisionIndex = fileName.indexOf(FileUtil.FILE_PATH_DIVISION);

        return fileName.substring(fileDivisionIndex + FileUtil.FILE_PATH_DIVISION.length());
    }

}
