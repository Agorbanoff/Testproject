package org.example.service;

import org.example.controller.model.UserCredentials;
import org.example.controller.model.UserProfile;
import org.example.exception.exceptions.UserNotFoundException;
import org.example.exception.exceptions.UsernameAlreadyExistsException;
import org.example.persistence.model.SessionEntity;

import java.io.IOException;

public interface UserAccountService {

    void signup(UserCredentials userCredentials) throws UsernameAlreadyExistsException;

    String login(UserCredentials userCredentials) throws UsernameAlreadyExistsException, UserNotFoundException;

    void logout(String sessionString);

    void setUserProfile(UserProfile userProfile, String sessionString) throws IOException;

}
