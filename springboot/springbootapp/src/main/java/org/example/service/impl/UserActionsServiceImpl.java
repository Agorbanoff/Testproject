package org.example.service.impl;

import org.example.controller.model.Subreddit;
import org.example.persistence.model.SubredditEntity;
import org.example.persistence.repository.SubredditRepository;
import org.example.persistence.repository.UserAccountRepository;
import org.example.service.UserActionsService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserActionsServiceImpl implements UserActionsService {
    @Autowired
    private SubredditRepository subredditRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public void createSubreddit(Subreddit subreddit) {
        subredditRepository.save(subreddit.toSubredditEntity());
    }

    @Override
    public void joinSubreddit(String sessionString, String subredditName) {
        SubredditEntity subredditEntity = subredditRepository.findByName(subredditName);
        subredditEntity.getUsers().add(userAccountRepository.findBySession_SessionString(sessionString));
        subredditRepository.save(subredditEntity);
    }

    @Override
    public void createPost(String title, String text, String sessionString, String subredditName) {

    }

}
