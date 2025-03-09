package org.example.controller;

import org.example.controller.model.CreateSubredditRequestDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface LoadDataController {
    @RequestMapping(value = "/loadsubreddits", method = RequestMethod.POST)
    ResponseEntity<List <CreateSubredditRequestDTO>> loadSubreddits(@RequestParam int count);
}

