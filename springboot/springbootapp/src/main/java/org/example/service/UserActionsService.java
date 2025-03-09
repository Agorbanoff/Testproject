package org.example.service;

import org.example.controller.model.CreatePostRequestDTO;
import org.example.controller.model.CreateSubredditRequestDTO;
import org.example.controller.model.JoinSubredditRequestDTO;
import org.example.exception.exceptions.SessionExpiredException;

public interface UserActionsService {
    void createSubreddit(String sessionString, CreateSubredditRequestDTO createSubredditRequestDTO) throws Exception;
    void joinSubreddit(String sessionString, JoinSubredditRequestDTO subreddit) throws Exception;
    void createPost(CreatePostRequestDTO createPostRequestDTO, String sessionString, JoinSubredditRequestDTO subreddit) throws Exception;
    void createComment(String sessionString, String text, Long upperCommentId, Long postId) throws SessionExpiredException;
}
