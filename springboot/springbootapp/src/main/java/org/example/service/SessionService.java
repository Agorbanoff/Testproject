package org.example.service;

import org.example.persistence.model.SessionEntity;

public interface SessionService {

    boolean saveUser(SessionEntity sessionEntity);

    boolean login(SessionEntity sessionEntity);
}
