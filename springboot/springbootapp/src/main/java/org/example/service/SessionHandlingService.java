package org.example.service;

import org.example.exception.exceptions.SessionExpiredException;

public interface SessionHandlingService {
    void updateSessionIfNotExpired(String session) throws SessionExpiredException;

}
