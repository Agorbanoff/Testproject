package org.example.persistence.repository;

import org.example.persistence.model.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {
    boolean existsByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);

}
