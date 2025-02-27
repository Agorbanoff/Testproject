package org.example.service;

public interface SessionHandlingService {
    void updateSessionIfNotExpired(String session);

}
