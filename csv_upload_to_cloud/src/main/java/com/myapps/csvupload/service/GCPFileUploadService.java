package com.myapps.csvupload.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@ConditionalOnProperty(value = "cloud.service", havingValue = "gcp")
public class GCPFileUploadService implements FileUploadService {


    @Value("${gcp.bucket.name}")
    private String bucketName;

    @Autowired
    private Storage storage;

    @Override
    public void uploadFileToCloud(File file) throws IOException {

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, file.getName())
                .setContentType("text/csv")
                .build();
        storage.create(blobInfo, Files.readAllBytes(Paths.get(file.toURI())));

        Files.deleteIfExists(Paths.get(file.toURI()));

    }
}
