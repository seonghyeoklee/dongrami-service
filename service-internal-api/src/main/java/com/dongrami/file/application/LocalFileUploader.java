package com.dongrami.file.application;

import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LocalFileUploader implements FileUploader {
    private static final String DIRECTORY = "/Users/woody/smallticket/projects/dongrami-service/";

    @Override
    public void uploadFile(String filename, byte[] file) {
        try {
            Path path = Paths.get(DIRECTORY + filename);
            Files.write(path, file);
        } catch (IOException e) {
            throw new BaseException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

}