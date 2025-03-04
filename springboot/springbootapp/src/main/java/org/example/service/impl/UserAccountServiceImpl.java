package org.example.service.impl;

import org.example.controller.model.UserCredentials;
import org.example.controller.model.UserProfile;
import org.example.exception.exceptions.UserNotFoundException;
import org.example.exception.exceptions.UsernameAlreadyExistsException;
import org.example.persistence.model.SessionEntity;
import org.example.persistence.model.UserAccountEntity;
import org.example.persistence.model.UserProfileEntity;
import org.example.persistence.repository.SessionRepository;
import org.example.persistence.repository.UserAccountRepository;
import org.example.persistence.repository.UserProfileRepository;
import org.example.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private static String PFP_DIR_PATH = "~/TestProject/UserData/Pfp";

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public void signup(UserCredentials userCredentials) throws UsernameAlreadyExistsException {
        if(userProfileRepository.existsByUsername(userCredentials.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists!");
        }
        UserProfileEntity userProfileEntity = userCredentials.toUserProfileEntity();
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setProfile(userProfileEntity);
        userAccountRepository.save(userAccountEntity);
        userProfileRepository.save(userProfileEntity);
    }

    @Override
    public String login(UserCredentials userCredentials) throws UserNotFoundException {
        if (!userProfileRepository.existsByUsername(userCredentials.getUsername())) {
                throw new UserNotFoundException("User not found!");
            }

        SessionEntity sessionEntity = setUpSessionEntity();
        String sessionString = sessionEntity.getSessionString();
        sessionRepository.save(sessionEntity);
        return sessionEntity.getSessionString();
    }

    @Override
    public void logout(String sessionString) {
        SessionEntity sessionEntity = sessionRepository.findBySessionString(sessionString);
        sessionEntity.setExpired(true);
    }

    @Override
    public void setUserProfile(UserProfile userProfile, String sessionString) throws IOException {
        if (isSessionExpired(sessionString)) {
            System.out.println("not ok");
        }
        String pfpPath = saveMultipartFile(userProfile.getPfp());
        long userProfileId = getUserProfileIdBySessionString(sessionString);
        UserProfileEntity userProfileEntity = userProfile.toUserProfileEntity(userProfileId, pfpPath);
        userProfileRepository.save(userProfileEntity);
    }


    private SessionEntity setUpSessionEntity() {
        UUID uuid = UUID.randomUUID();
        long expirationDateInMillis = System.currentTimeMillis() + 900000;
        return new SessionEntity(uuid.toString(), expirationDateInMillis);
    }
    private boolean isSessionExpired(String sessionString) {
        SessionEntity sessionEntity = sessionRepository.findBySessionString(sessionString);
        return sessionEntity.isExpired();
    }
    private long getUserProfileIdBySessionString(String sessionString) {
        UserAccountEntity userAccountEntity = userAccountRepository.findBySession_SessionString(sessionString);
        UserProfileEntity userProfileEntity = userAccountEntity.getProfile();
        return userProfileEntity.getId();
    }
    private String saveMultipartFile(MultipartFile file) throws IOException {
        Path fileDir = Path.of(PFP_DIR_PATH);
        Files.createDirectories(fileDir);
        Path filePath = fileDir.resolve(file.getOriginalFilename() + String.valueOf(System.currentTimeMillis()));
        Files.copy(file.getInputStream(), filePath);
        return filePath.toString();
    }
}

