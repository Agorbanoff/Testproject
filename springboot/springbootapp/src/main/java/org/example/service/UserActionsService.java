package org.example.service;

import org.example.controller.model.Subreddit;

public interface UserActionsService {
    void createSubreddit(Subreddit subreddit);
    void joinSubreddit(String sessionString, String subredditName);
    void createPost(String title, String text, String sessionString, String subredditName);
}
