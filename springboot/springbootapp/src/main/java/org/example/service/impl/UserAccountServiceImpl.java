package org.example.service.impl;

import org.example.controller.model.UserCredentials;
import org.example.controller.model.UserProfile;
import org.example.controller.model.ValidationData;
import org.example.exception.exceptions.SessionExpiredException;
import org.example.persistence.model.SessionEntity;
import org.example.persistence.model.UserAccountEntity;
import org.example.persistence.model.UserProfileEntity;
import org.example.persistence.repository.SessionRepository;
import org.example.persistence.repository.UserAccountRepository;
import org.example.persistence.repository.UserProfileRepository;
import org.example.service.FileService;
import org.example.service.UserAccountService;
import org.example.service.ValidationService;
import org.example.validations.LoginValidation;
import org.example.validations.SignUpValidation;
import org.example.validations.Validation;
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


    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ValidationServiceImpl validationService;
    @Autowired
    private SessionHandlingServiceImpl sessionHandlingService;
    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public void signup(UserCredentials userCredentials) throws Exception {
        validationService.validate(SignUpValidation.class, ValidationData.builder().userCredentials(userCredentials).build());
        UserProfileEntity userProfileEntity = (UserProfileEntity) userCredentials.toEntity();
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setProfile(userProfileEntity);
        userAccountRepository.save(userAccountEntity);
    }

    @Override
    public String login(UserCredentials userCredentials) throws Exception {
        validationService.validate(LoginValidation.class, ValidationData.builder().userCredentials(userCredentials).build());
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
//        validationService.validate(LoginValidation.class, ValidationData.builder().userProfile(userProfile).build());
        String pfpPath = fileService.savePfp(userProfile.getPfp(), sessionString);
        long userProfileId = getUserProfileIdBySessionString(sessionString);
        UserProfileEntity userProfileEntity = (UserProfileEntity) userProfile.toEntity();
        userProfileEntity.setId(userProfileId);
        userProfileEntity.setPfpPath("pfp");
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



}

