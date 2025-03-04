package org.example.persistence.repository;

import org.example.persistence.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedditRepository<T extends BaseEntity, S extends Number> extends JpaRepository<T, S> {

}
