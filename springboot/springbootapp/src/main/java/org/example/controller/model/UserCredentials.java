package org.example.controller.model;

import lombok.Data;
import org.example.persistence.model.UserProfileEntity;

@Data
public class UserCredentials {
    private String username;
    private String password;

    public UserProfileEntity toUserProfileEntity() {
        UserProfileEntity userProfileEntity = new UserProfileEntity();
        userProfileEntity.setUsername(username);
        userProfileEntity.setPassword(password);
        return userProfileEntity;
    }
}
