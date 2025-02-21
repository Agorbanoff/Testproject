package org.example.controller.model;

import lombok.Data;
import org.example.persistence.model.UserProfileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Data
public class UserProfile {
    private String username;
    private String password;
    private MultipartFile pfp;
    private String description;

    public UserProfileEntity toUserProfileEntity(long profileId, String pfpPath) {
        UserProfileEntity userProfileEntity = new UserProfileEntity();
        userProfileEntity.setProfileId(profileId);
        userProfileEntity.setUsername(username);
        userProfileEntity.setPassword(password);
        userProfileEntity.setPfpPath(pfpPath);
        userProfileEntity.setDescription(description);
        return userProfileEntity;
    }
}
