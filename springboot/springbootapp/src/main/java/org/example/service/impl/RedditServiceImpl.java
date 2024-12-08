package org.example.service.impl;

import org.example.persistence.repository.RedditRepository;
import org.example.service.RedditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedditServiceImpl implements RedditService {
    @Autowired
    private RedditRepository repo;
}
