package org.example.controller;

import org.springframework.http.ResponseEntity;

public interface UserActionsController {
    public ResponseEntity<String> createSubreddit();
    public ResponseEntity<String> joinSubreddit();
    public ResponseEntity<String> createPost();
    public ResponseEntity<String> createComment();
}
