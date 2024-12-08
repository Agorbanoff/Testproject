package org.example.persistence.repository;

import org.example.persistence.model.RedditEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RedditRepository extends JpaRepository<RedditEntity, Integer> {
}
