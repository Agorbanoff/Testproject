package org.example.controller;

import org.example.controller.model.UserCredentials;
import org.example.controller.model.UserProfile;
import org.example.exception.exceptions.UserNotFoundException;
import org.example.exception.exceptions.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

public interface UserAccountController {
    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<String> signup(@RequestBody UserCredentials userCredentials) throws Exception;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<String> login(@RequestBody UserCredentials userCredentials) throws Exception;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    ResponseEntity<String> logout(@RequestParam String sessionString);

    @RequestMapping(value = "/profile", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> setProfile(@ModelAttribute UserProfile userProfile, @RequestParam String sessionString) throws IOException;

}
