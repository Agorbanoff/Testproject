package org.example.controller;

import org.example.controller.model.Subreddit;
import org.example.exception.exceptions.SubredditAlreadyExistsException;
import org.example.exception.exceptions.SubredditNotFoundException;
import org.example.persistence.model.CommentEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserActionsController {
    @RequestMapping(value = "/createsubreddit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
     ResponseEntity<String> createSubreddit(@RequestBody Subreddit subreddit) throws SubredditAlreadyExistsException;
    @RequestMapping(value = "/joinsubreddit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
     ResponseEntity<String> joinSubreddit(@RequestParam String sessionString, @RequestParam Long subredditId);
    @RequestMapping(value = "/createpost", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
     ResponseEntity<String> createPost(@RequestParam String title, @RequestParam String text, @RequestParam String sessionString, @RequestParam Long subredditId) throws SubredditNotFoundException;
    @RequestMapping(value = "/createcomment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
     ResponseEntity<String> createComment(@RequestParam String sessionString, @RequestParam String text, @RequestParam Long upperCommentId, @RequestParam Long postId);
}
