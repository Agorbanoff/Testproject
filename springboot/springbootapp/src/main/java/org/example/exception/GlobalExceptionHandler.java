package org.example.exception;

import org.example.exception.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("User not found!");
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("User already exists");
    }

    @ExceptionHandler(SessionExpiredException.class)
    public ResponseEntity<String> handleSessionExpiredException() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Session expired!");
    }

    @ExceptionHandler(SubredditAlreadyExistsException.class)
    public ResponseEntity<String> handleSubredditAlreadyExistsException() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Subreddit already exists!");
    }

    @ExceptionHandler(SubredditNotFoundException.class)
    public ResponseEntity<String> handleSubredditNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Subreddit not found!");
    }
}
