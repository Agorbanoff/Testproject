package org.example.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String savePfp(MultipartFile file, String sessionString) throws IOException;
}
