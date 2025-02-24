package org.example.persistence.repository;

import org.example.persistence.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query("SELECT p FROM PostEntity p WHERE p.id = :id")
    PostEntity findPostByPostId(@Param("id") Long id);
}
