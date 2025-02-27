package org.example.controller.impl;

import org.example.controller.UserActionsController;
import org.example.controller.model.Subreddit;
import org.example.exception.exceptions.SubredditAlreadyExistsException;
import org.example.exception.exceptions.SubredditNotFoundException;
import org.example.persistence.model.CommentEntity;
import org.example.service.impl.UserActionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UserActionsControllerImpl implements UserActionsController {
    @Autowired
    private UserActionsServiceImpl userActionsService;

    @Override
    public ResponseEntity<String> createSubreddit(Subreddit subreddit) throws SubredditAlreadyExistsException {
        userActionsService.createSubreddit(subreddit);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("OK");
    }

    @Override
    public ResponseEntity<String> joinSubreddit(String sessionString, Long subredditId) {
        userActionsService.joinSubreddit(sessionString, subredditId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("OK");
    }

    @Override
    public ResponseEntity<String> createPost(String title, String text, String sessionString, Long subredditId) throws SubredditNotFoundException {
        userActionsService.createPost(title, text, sessionString, subredditId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("CREATED");
    }

    @Override
    public ResponseEntity<String> createComment(String sessionString, String text, Long upperCommentId, Long postId) {
        userActionsService.createComment(sessionString, text, upperCommentId, postId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("CREATED");
    }
}