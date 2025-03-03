package org.example.persistence.repository;

import org.example.persistence.model.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends RedditRepository<UserAccountEntity, Long> {
    UserAccountEntity findBySession_SessionString(String sessionString);
    UserAccountEntity findByProfile_Username(String username);
}
