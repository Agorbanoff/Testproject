package org.example.service.impl;

import org.example.persistence.repository.SubredditRepository;
import org.example.service.LoadDataService;
import org.springframework.beans.factory.annotation.Autowired;

public class LoadDataServiceImpl implements LoadDataService {

    @Autowired
    SubredditRepository subredditRepository;

    @Override
    public void loadSubreddits(int subredditCount) {
        
    }
}
