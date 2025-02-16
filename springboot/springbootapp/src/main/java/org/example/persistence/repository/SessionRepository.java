package org.example.persistence.repository;

import org.example.persistence.model.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
    SessionEntity findBySessionString(String sessionString);
    boolean existsBySessionString(String sessionString);
}
