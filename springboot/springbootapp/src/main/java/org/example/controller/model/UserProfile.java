package org.example.controller.model;

import lombok.Data;
import org.example.persistence.model.BaseEntity;
import org.example.persistence.model.PostEntity;
import org.example.persistence.model.UserProfileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Data
public class UserProfile extends DTO{
    private String username;
    private String password;
    private MultipartFile pfp;
    private String description;

    @Override
    public BaseEntity toEntity() {
        UserProfileEntity userProfileEntity = new UserProfileEntity();
        userProfileEntity.setUsername(username);
        userProfileEntity.setPassword(password);
        userProfileEntity.setPfpPath("pfp");
        userProfileEntity.setDescription(description);
        return userProfileEntity;
    }
}
