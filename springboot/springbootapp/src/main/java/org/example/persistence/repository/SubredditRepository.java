package org.example.persistence.repository;

import org.example.controller.model.CreateSubredditRequestDTO;
import org.example.persistence.model.SubredditEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubredditRepository extends RedditRepository<SubredditEntity, Long> {
    SubredditEntity findByName(String name);
    boolean existsByName(String name);
    boolean existsByUsers_Session_SessionStringAndId(String sessionString, Long subredditId);
    @Query("SELECT new org.example.controller.model.CreateSubredditRequestDTO(s.name, s.description) FROM SubredditEntity s ORDER BY s.id DESC")
    List<CreateSubredditRequestDTO> findTopSubreddits(Pageable pageable);
}
