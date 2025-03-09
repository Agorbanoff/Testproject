package org.example.service;

import org.example.controller.model.CreateSubredditRequestDTO;

import java.util.List;

public interface LoadDataService {
    List<CreateSubredditRequestDTO> loadSubreddits(int subredditCount);
    
}
