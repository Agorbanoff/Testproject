package org.example.service;

import org.example.controller.model.Post;
import org.example.controller.model.Subreddit;
import org.example.exception.exceptions.SessionExpiredException;
import org.example.exception.exceptions.SubredditAlreadyExistsException;
import org.example.exception.exceptions.SubredditNotFoundException;

public interface UserActionsService {
    void createSubreddit(String sessionString, Subreddit subreddit) throws Exception;
    void joinSubreddit(String sessionString, Long subredditId) throws Exception;
    void createPost(Post post, String sessionString, Long subredditId) throws Exception;
    void createComment(String sessionString, String text, Long upperCommentId, Long postId) throws SessionExpiredException;
}
