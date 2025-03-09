package org.example.service.impl;

import org.example.controller.model.UserProfile;
import org.example.persistence.model.UserAccountEntity;
import org.example.persistence.model.UserProfileEntity;
import org.example.persistence.repository.UserAccountRepository;
import org.example.persistence.repository.UserProfileRepository;
import org.example.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {
    private final String PFP_DIR_PATH = "~/TestProject/UserData/Pfp";

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public String savePfp(MultipartFile file, String sessionString) throws IOException {
        Path fileDir = Path.of(PFP_DIR_PATH);
        Files.createDirectories(fileDir);
        Path filePath = fileDir.resolve(file.getOriginalFilename() + String.valueOf(System.currentTimeMillis()));
        Files.copy(file.getInputStream(), filePath);
        return filePath.toString();
    }
}
