package org.example.persistence.repository;

import org.example.controller.model.Subreddit;
import org.example.persistence.model.SubredditEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubredditRepository extends JpaRepository<SubredditEntity, Long> {
    SubredditEntity findByName(String name);
}
