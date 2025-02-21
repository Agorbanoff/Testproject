package org.example.persistence.repository;

import org.example.persistence.model.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {
    boolean existsByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);

}
