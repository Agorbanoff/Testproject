package org.example.service.impl;

import org.example.exception.exceptions.SessionExpiredException;
import org.example.persistence.model.SessionEntity;
import org.example.persistence.repository.SessionRepository;
import org.example.service.SessionHandlingService;
import org.example.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SessionHandlingServiceImpl implements SessionHandlingService {

    @Autowired
    private SessionRepository sessionRepository;
    @Override
    public void updateSessionIfNotExpired(String sessionString) throws SessionExpiredException {
        if (!isSessionExisting(sessionString) || isExpired(sessionString)) {
            throw new SessionExpiredException("Session expired!");
        }
        SessionEntity sessionEntity = sessionRepository.findBySessionString(sessionString);
        sessionEntity.setExpirationDateInMillis(System.currentTimeMillis() + 900000);
    }

    private boolean isExpired(String sessionString) {
        SessionEntity sessionEntity = sessionRepository.findBySessionString(sessionString);
        return sessionEntity.getExpirationDateInMillis() < System.currentTimeMillis();
    }

    private boolean isSessionExisting(String sessionString) {
        return sessionRepository.existsBySessionString(sessionString);
    }
}
