package com.ssafy.project.common.provider;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface S3Provider {

    String uploadFile(File file, String uri) throws IOException;
    String uploadMultipartFile(MultipartFile file, String uri) throws IOException;
    void delete(String uri, int start);
}
