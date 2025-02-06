package org.example.service.impl;

import org.example.persistence.model.SessionEntity;
import org.example.persistence.repository.SessionRepository;
import org.example.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public boolean saveUser(SessionEntity sessionEntity) {
        boolean isUserExisting = isUserExisting(sessionEntity);
        if(!isUserExisting){
            save(sessionEntity);
        }
        return !isUserExisting;

    }

    @Override
    public boolean login(SessionEntity sessionEntity) {
        return isUserExisting(sessionEntity);
    }

    private void save(SessionEntity sessionEntity) {
        sessionRepository.save(sessionEntity);

    }
    private boolean isUserExisting(SessionEntity sessionEntity) {
        return sessionRepository.existsByUsernameAndPassword(sessionEntity.getUsername(),
                sessionEntity.getPassword());
    }

}
