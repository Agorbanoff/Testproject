package org.example.controller.impl;

import org.example.controller.UserActionsController;
import org.example.controller.model.Subreddit;
import org.example.service.impl.UserActionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserActionsControllerImpl implements UserActionsController {
    @Autowired
    private UserActionsServiceImpl userActionsService;

    @Override
    public ResponseEntity<String> createSubreddit(Subreddit subreddit) {
        userActionsService.createSubreddit(subreddit);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("OK");
    }

    @Override
    public ResponseEntity<String> joinSubreddit(String sessionString, String subredditName) {
        userActionsService.joinSubreddit(sessionString, subredditName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("OK");
    }

    @Override
    public ResponseEntity<String> createPost(String title, String text, String sessionString, String subredditName) {
        return null;
    }


    @Override
    public ResponseEntity<String> createComment() {
        return null;
    }
}
