package org.example.controller.impl;

import org.example.controller.FileController;
import org.example.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileControllerImpl implements FileController {
    @Autowired
    private FileServiceImpl fileService;

    @Override
    public ResponseEntity<String> setPfp(MultipartFile pfp, String sessionString) throws IOException {
        fileService.savePfp(pfp, sessionString);
        return null;
    }
}
