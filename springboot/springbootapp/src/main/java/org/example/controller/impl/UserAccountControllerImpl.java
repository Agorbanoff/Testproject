package org.example.controller.impl;

import org.example.controller.UserAccountController;
import org.example.controller.model.UserCredentials;
import org.example.controller.model.UserProfile;
import org.example.exception.exceptions.UserNotFoundException;
import org.example.exception.exceptions.UsernameAlreadyExistsException;
import org.example.service.impl.UserAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

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
    public ResponseEntity<String> signup(UserCredentials userCredentials) throws UsernameAlreadyExistsException {
        HttpStatus status = HttpStatus.CREATED;
        String body = "Signed up successfully!";
        userAccountService.signup(userCredentials);
        return ResponseEntity
                .status(status)
                .body(body);
    }

    @Override
    public ResponseEntity<String> login(UserCredentials userCredentials) throws UserNotFoundException {
        HttpStatus status = HttpStatus.OK;
        String body = userAccountService.login(userCredentials);
        return ResponseEntity
                .status(status)
                .body(body);
    }

    @Override
    public ResponseEntity<String> logout(String sessionString) {
        userAccountService.logout(sessionString);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("ok");
    }

    @Override
    public ResponseEntity<String> setProfile(UserProfile userProfile, String sessionString) throws IOException {
        userAccountService.setUserProfile(userProfile, sessionString);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
}
