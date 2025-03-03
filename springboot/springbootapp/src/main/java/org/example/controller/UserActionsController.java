package org.example.controller;

import org.example.controller.model.Post;
import org.example.controller.model.Subreddit;
import org.example.exception.exceptions.SessionExpiredException;
import org.example.exception.exceptions.SubredditAlreadyExistsException;
import org.example.exception.exceptions.SubredditNotFoundException;
import org.example.persistence.model.CommentEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserActionsController {
    @RequestMapping(value = "/s", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
     ResponseEntity<String> createSubreddit(@CookieValue(value = "SESSION_STRING") String sessionString , @RequestBody Subreddit subreddit) throws Exception;
    @RequestMapping(value = "/joinsubreddit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
     ResponseEntity<String> joinSubreddit(@CookieValue(value = "SESSION_STRING") String sessionString, @RequestParam("subredditId") Long subredditId) throws Exception;
    @RequestMapping(value = "/createpost", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
     ResponseEntity<String> createPost(@RequestBody Post post, @CookieValue(value = "SESSION_STRING") String sessionString, @RequestParam("subredditId") Long subredditId) throws Exception;
    @RequestMapping(value = "/createcomment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
     ResponseEntity<String> createComment(@CookieValue(value = "SESSION_STRING") String sessionString, @RequestParam("text") String text, @RequestParam("upperCommentId") Long upperCommentId, @RequestParam("postId") Long postId) throws SessionExpiredException;
}
