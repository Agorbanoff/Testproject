package org.example.service.impl;

import org.example.controller.model.CreateSubredditRequestDTO;
import org.example.persistence.repository.SubredditRepository;
import org.example.service.LoadDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadDataServiceImpl implements LoadDataService {

    @Autowired
    SubredditRepository subredditRepository;

    @Override
    public List<CreateSubredditRequestDTO> loadSubreddits(int subredditCount) {
        Pageable pageable = PageRequest.of(0, subredditCount, Sort.by("id").descending());
        return subredditRepository.findTopSubreddits(pageable);
    }
}
