package com.myapps.csvupload.service;

import java.io.File;
import java.io.IOException;

public interface FileUploadService {

    void uploadFileToCloud(File file) throws IOException;
}
