package org.example.service;

import org.example.controller.model.Subreddit;
import org.example.exception.exceptions.SubredditAlreadyExistsException;
import org.example.exception.exceptions.SubredditNotFoundException;

public interface UserActionsService {
    void createSubreddit(Subreddit subreddit) throws SubredditAlreadyExistsException;
    void joinSubreddit(String sessionString, Long subredditId);
    void createPost(String title, String text, String sessionString, Long subredditId) throws SubredditNotFoundException;
    void createComment(String sessionString, String text, Long upperCommentId, Long postId);
}
