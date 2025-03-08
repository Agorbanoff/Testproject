package org.example.persistence.repository;

import org.example.controller.model.Subreddit;
import org.example.persistence.model.SubredditEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubredditRepository extends RedditRepository<SubredditEntity, Long> {
    SubredditEntity findByName(String name);
    boolean existsByName(String name);
    boolean existsByUsers_Session_SessionStringAndId(String sessionString, Long subredditId);
    @Query(value = "SELECT s FROM subreddit_table s ORDER BY s.id DESC")
    List<Subreddit> findTopSubreddits(Pageable pageable);
}
