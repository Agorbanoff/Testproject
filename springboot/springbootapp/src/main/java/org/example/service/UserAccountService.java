package org.example.service;

import org.example.controller.model.UserCredentials;
import org.example.controller.model.UserProfile;
import org.example.exception.exceptions.SessionExpiredException;
import org.example.exception.exceptions.UserNotFoundException;
import org.example.exception.exceptions.UsernameAlreadyExistsException;
import org.example.persistence.model.SessionEntity;

import java.io.IOException;

public interface UserAccountService {

    void signup(UserCredentials userCredentials) throws Exception;

    String login(UserCredentials userCredentials) throws Exception;

    void logout(String sessionString) throws SessionExpiredException;

    void setUserProfile(UserProfile userProfile, String sessionString) throws Exception;

}
