package org.example.service.impl;

import org.example.persistence.repository.SessionRepository;
import org.example.service.SessionHandlingService;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionHandlingServiceImpl implements SessionHandlingService {
    @Autowired
    private SessionRepository sessionRepository;
    @Override
    public void updateSessionIfNotExpired(String sessionString) {
        if (sessionRepository.existsBySessionString(sessionString) && sessionRepository.findBySessionString(sessionString).isExpired());
    }
}
