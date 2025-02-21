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
    private long id;

    @OneToOne
    @JoinColumn(name = "userAccountId", referencedColumnName = "id")
    private UserAccountEntity creator;

    @Column
    private String title;

    @Column
    private String text;

    @OneToMany(mappedBy = "post")
    private List<CommentEntity> comments;
}
