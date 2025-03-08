package org.example.controller.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationData {
    private UserCredentials userCredentials;
    private UserProfile userProfile;
    private String sessionString;
    private Subreddit subreddit;
    private Post post;
}
