package org.example.validators;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Validators {
    NULL_ID_IN_CREATE_REQUEST_VALIDATOR("nullIdInCreateRequestException"),
    UNIQUE_USERNAME_VALIDATOR("uniqueUsernameValidator"),
    EXISTING_USER_CREDENTIALS_VALIDATOR("existingUserCredentialsValidator"),
    UNIQUE_SUBREDDIT_VALIDATOR("uniqueSubredditValidator"),
    EXISTING_SUBREDDIT_VALIDATOR("existingSubredditValidator"),
    USER_ALREADY_JOINED_SUBREDDIT_VALIDATOR("userAlreadyJoinedSubredditValidator");

    private final String value;
}
