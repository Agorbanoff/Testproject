package org.example.service.impl;

import org.example.controller.model.Subreddit;
import org.example.persistence.repository.SubredditRepository;
import org.example.service.UserActionsService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserActionsServiceImpl implements UserActionsService {
    @Autowired
    private SubredditRepository subredditRepository;

    @Override
    public void createSubreddit(Subreddit subreddit) {
        subredditRepository.save(subreddit.toSubredditEntity());
    }

}
