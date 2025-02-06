package org.example.service.impl;

import org.example.persistence.model.UserProfileEntity;
import org.example.persistence.repository.UserProfileRepository;
import org.example.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileRepository repo;

    public void save() {
        UserProfileEntity entity = new UserProfileEntity();
        entity.setUsername("test");
        entity.setPassword("password");
        repo.save(entity);
    }
}
