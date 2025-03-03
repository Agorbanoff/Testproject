package org.example.controller.impl;

import org.example.controller.UserAccountController;
import org.example.controller.model.UserCredentials;
import org.example.controller.model.UserProfile;
import org.example.exception.exceptions.SessionExpiredException;
import org.example.exception.exceptions.UserNotFoundException;
import org.example.exception.exceptions.UsernameAlreadyExistsException;
import org.example.service.impl.UserAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/reddit")
public class UserAccountControllerImpl implements UserAccountController {

    @Autowired
    private UserAccountServiceImpl userAccountService;

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    @ResponseBody
//    public String test() {
//        service.save();
//        return "ok";
//    }

    @Override
    public ResponseEntity<String> signup(UserCredentials userCredentials) throws Exception {
        userAccountService.signup(userCredentials);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Signed up successfully!");
    }

    @Override
    public ResponseEntity<String> login(UserCredentials userCredentials) throws Exception {
        String sessionString = userAccountService.login(userCredentials);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, String.format("SESSION_STRING=%s;  HttpOnly; Path=/; Secure; SameSite=Strict", sessionString));
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body("Logged in successfully!");
    }

    @Override
    public ResponseEntity<String> logout(String sessionString) throws SessionExpiredException {
        userAccountService.logout(sessionString);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Logged out");
    }

    @Override
    public ResponseEntity<String> setProfile(UserProfile userProfile, String sessionString) throws Exception {
        userAccountService.setUserProfile(userProfile, sessionString);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
}
