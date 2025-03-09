package org.example.controller.model;

import lombok.Data;
import org.example.persistence.model.BaseEntity;
import org.example.persistence.model.PostEntity;
import org.example.persistence.model.UserProfileEntity;

@Data
public class UserCredentials extends DTO{
    private String username;
    private String password;

    @Override
    public BaseEntity toEntity() {
        UserProfileEntity userProfileEntity = new UserProfileEntity();
        userProfileEntity.setUsername(username);
        userProfileEntity.setPassword(password);
        return userProfileEntity;
    }
}
