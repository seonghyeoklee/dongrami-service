package com.dongrami.file.ui;

import com.dongrami.common.ApiResponse;
import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.file.application.FileUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FileController {
    private final Environment environment;
    private final FileUploader s3FileUploader;
    private final FileUploader localFileUploader;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            for (String activeProfile : environment.getActiveProfiles()) {
                if (activeProfile.equals("local")) {
                    localFileUploader.uploadFile(file.getOriginalFilename(), file.getBytes());
                } else if (activeProfile.equals("dev") || activeProfile.equals("prd")) {
                    s3FileUploader.uploadFile(file.getOriginalFilename(), file.getBytes());
                }
            }
            return ResponseEntity.ok().body(ApiResponse.success());

        } catch (IOException e) {
            throw new BaseException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

}
