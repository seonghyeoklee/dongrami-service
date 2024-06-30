package com.dongrami.file.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class S3FileUploader implements FileUploader {
    private final AmazonS3 amazonS3;

    private static final String BUCKET_NAME = "your-bucket-name";

    @Override
    public void uploadFile(String filename, byte[] file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.length);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(file);
        amazonS3.putObject(BUCKET_NAME, filename, inputStream, metadata);
    }

}
