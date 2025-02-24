package org.example.persistence.repository;

import org.example.persistence.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query("SELECT comment FROM CommentEntity comment WHERE comment.id = :id")
    CommentEntity findCommentByCommentId(@Param("id") Long id);
}
