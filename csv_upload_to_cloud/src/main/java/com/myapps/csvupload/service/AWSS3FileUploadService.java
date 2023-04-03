package com.myapps.csvupload.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Service
@ConditionalOnProperty(value = "cloud.service", havingValue = "aws")
@RequiredArgsConstructor
public class AWSS3FileUploadService implements FileUploadService {

    private final AmazonS3 amazonS3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Override
    public void uploadFileToCloud(File file) throws IOException {
        try {
            PutObjectRequest request = new PutObjectRequest(bucketName,
                    file.getName(), file);
            amazonS3Client.putObject(request);
            Files.deleteIfExists(Paths.get(file.toURI()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
