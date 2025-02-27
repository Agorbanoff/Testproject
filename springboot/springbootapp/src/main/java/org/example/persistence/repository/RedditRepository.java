package org.example.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RedditRepository<T, S> extends JpaRepository<T, S> {

}
