package org.example.controller.impl;

import org.example.controller.UserActionsController;
import org.example.controller.model.Post;
import org.example.controller.model.Subreddit;
import org.example.exception.exceptions.SessionExpiredException;
import org.example.exception.exceptions.SubredditAlreadyExistsException;
import org.example.exception.exceptions.SubredditNotFoundException;
import org.example.persistence.model.CommentEntity;
import org.example.service.impl.UserActionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reddit")
public class UserActionsControllerImpl implements UserActionsController {
    @Autowired
    private UserActionsServiceImpl userActionsService;

    @Override
    public ResponseEntity<String> createSubreddit(String sessionString, Subreddit subreddit) throws Exception {
        userActionsService.createSubreddit(sessionString ,subreddit);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Created subreddit!");
    }

    @Override
    public ResponseEntity<String> joinSubreddit(String sessionString, Long subredditId) throws Exception {
        userActionsService.joinSubreddit(sessionString, subredditId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Joined subreddit!");
    }

    @Override
    public ResponseEntity<String> createPost(Post post, String sessionString, Long subredditId) throws Exception {
        userActionsService.createPost(post, sessionString, subredditId);
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