package org.example.exception.exceptions;

public class UserNotInSubredditException extends Exception{
    public UserNotInSubredditException(String msg) {
        super(msg);
    }
}
