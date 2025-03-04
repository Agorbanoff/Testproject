package org.example.service.impl;

import org.example.controller.model.UserCredentials;
import org.example.controller.model.UserProfile;
import org.example.exception.exceptions.SessionExpiredException;
import org.example.persistence.model.SessionEntity;
import org.example.persistence.model.UserAccountEntity;
import org.example.persistence.model.UserProfileEntity;
import org.example.persistence.repository.SessionRepository;
import org.example.persistence.repository.UserAccountRepository;
import org.example.persistence.repository.UserProfileRepository;
import org.example.service.UserAccountService;
import org.example.validators.ExistingUserCredentialsValidator;
import org.example.validators.UniqueUsernameValidator;
import org.example.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private static String PFP_DIR_PATH = "~/TestProject/UserData/Pfp";

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SessionHandlingServiceImpl sessionHandlingService;

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public void signup(UserCredentials userCredentials) throws Exception {
        Map<Class<? extends Validator>, Object> validationPairs = new HashMap<>();
        validationPairs.put(UniqueUsernameValidator.class, userCredentials.getUsername());
        validate(validationPairs);
        UserProfileEntity userProfileEntity = userCredentials.toUserProfileEntity();
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setProfile(userProfileEntity);
        userAccountRepository.save(userAccountEntity);
    }

    @Override
    public String login(UserCredentials userCredentials) throws Exception {
        Map<Class<? extends Validator>, Object> validationPairs = new HashMap<>();
        validationPairs.put(ExistingUserCredentialsValidator.class, userCredentials);
        validate(validationPairs);
        UserAccountEntity userAccountEntity = userAccountRepository.findByProfile_Username(userCredentials.getUsername());
        SessionEntity sessionEntity = setUpSession();
        userAccountEntity.setSession(sessionEntity);
        String sessionString = sessionEntity.getSessionString();
        userAccountRepository.save(userAccountEntity);
        return sessionEntity.getSessionString();
    }

    @Override
    public void logout(String sessionString) throws SessionExpiredException {
        sessionHandlingService.updateSessionIfNotExpired(sessionString);
        SessionEntity sessionEntity = sessionRepository.findBySessionString(sessionString);
        sessionEntity.setExpired(true);
    }

    @Override
    public void setUserProfile(UserProfile userProfile, String sessionString) throws Exception {
        sessionHandlingService.updateSessionIfNotExpired(sessionString);
        Map<Class<? extends Validator>, Object> validationPairs = new HashMap<>();
        validationPairs.put(UniqueUsernameValidator.class, userProfile.getUsername());
        validate(validationPairs);
        String pfpPath = saveMultipartFile(userProfile.getPfp());
        long userProfileId = getUserProfileIdBySessionString(sessionString);
        UserProfileEntity userProfileEntity = userProfile.toUserProfileEntity(userProfileId, pfpPath);
        userProfileRepository.save(userProfileEntity);
    }


    private SessionEntity setUpSession() {
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

    private void validate(Map<Class<? extends Validator>, Object> validationPairs) throws Exception {
        for (Map.Entry<Class<? extends Validator>, Object> entry : validationPairs.entrySet()) {
            Validator validator = applicationContext.getBean(entry.getKey());
            validator.validate(entry.getValue());
        }

    }
}

