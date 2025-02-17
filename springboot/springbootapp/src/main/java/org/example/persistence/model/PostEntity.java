package org.example.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "posts_table")
@Data
public class PostEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;

    @OneToOne
    @JoinColumn
    private UserAccountEntity creator;
    
    private List<CommentEntity> comments;
}
