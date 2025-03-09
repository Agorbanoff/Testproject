package org.example.controller.impl;

import org.example.controller.UserActionsController;
import org.example.controller.model.CreatePostRequestDTO;
import org.example.controller.model.CreateSubredditRequestDTO;
import org.example.controller.model.JoinSubredditRequestDTO;
import org.example.exception.exceptions.SessionExpiredException;
import org.example.service.impl.UserActionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reddit")
@CrossOrigin(origins = "http://localhost:5173")
public class UserActionsControllerImpl implements UserActionsController {
    @Autowired
    private UserActionsServiceImpl userActionsService;

    @Override
    public ResponseEntity<String> createSubreddit(String sessionString, CreateSubredditRequestDTO createSubredditRequestDTO) throws Exception {
        userActionsService.createSubreddit(sessionString , createSubredditRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Created subreddit!");
    }

    @Override
    public ResponseEntity<String> joinSubreddit(String sessionString, JoinSubredditRequestDTO subreddit) throws Exception {
        userActionsService.joinSubreddit(sessionString, subreddit);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Joined subreddit!");
    }

    @Override
    public ResponseEntity<String> createPost(CreatePostRequestDTO createPostRequestDTO, String sessionString, JoinSubredditRequestDTO subreddit) throws Exception {
        userActionsService.createPost(createPostRequestDTO, sessionString, subreddit);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Post created!");
    }

    @Override
    public ResponseEntity<String> createComment(String sessionString, String text, Long upperCommentId, Long postId) throws SessionExpiredException {
        userActionsService.createComment(sessionString, text, upperCommentId, postId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Comment created!");
    }
}