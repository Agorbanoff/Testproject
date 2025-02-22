package org.example.service.impl;

import org.example.persistence.repository.SessionRepository;
import org.example.service.CleanUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CleanUpServiceImpl extends Thread implements CleanUpService {
    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public void run() {
        while (true) {
            sessionRepository.deleteByExpirationDateInMillisLessThan(System.currentTimeMillis());
            try {
                sleep(900000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
