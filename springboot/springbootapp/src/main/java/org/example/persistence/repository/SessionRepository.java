package org.example.persistence.repository;

import org.example.persistence.model.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends RedditRepository<SessionEntity, Long> {
    SessionEntity findBySessionString(String sessionString);
    boolean existsBySessionString(String sessionString);
    void deleteByExpirationDateInMillisLessThan(long currTimeInMillis);
}
