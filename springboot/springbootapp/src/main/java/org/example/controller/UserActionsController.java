package org.example.controller;

import org.example.controller.model.CreatePostRequestDTO;
import org.example.controller.model.CreateSubredditRequestDTO;
import org.example.controller.model.JoinSubredditRequestDTO;
import org.example.exception.exceptions.SessionExpiredException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserActionsController {
    @RequestMapping(value = "/createsubreddit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
     ResponseEntity<String> createSubreddit(@CookieValue(value = "SESSION_STRING") String sessionString , @RequestBody CreateSubredditRequestDTO createSubredditRequestDTO) throws Exception;
    @RequestMapping(value = "/joinsubreddit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
     ResponseEntity<String> joinSubreddit(@CookieValue(value = "SESSION_STRING") String sessionString, @RequestBody JoinSubredditRequestDTO subreddit) throws Exception;
    @RequestMapping(value = "/createpost", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
     ResponseEntity<String> createPost(@RequestPart("post") CreatePostRequestDTO createPostRequestDTO, @CookieValue(value = "SESSION_STRING") String sessionString, @RequestPart("subreddit") JoinSubredditRequestDTO joinSubredditRequestDTO) throws Exception;
    @RequestMapping(value = "/createcomment", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
     ResponseEntity<String> createComment(@CookieValue(value = "SESSION_STRING") String sessionString, @RequestParam("text") String text, @RequestParam("upperCommentId") Long upperCommentId, @RequestParam("postId") Long postId) throws SessionExpiredException;
}
