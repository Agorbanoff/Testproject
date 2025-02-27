package org.example.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.controller.model.Subreddit;

import java.util.List;

@Entity
@Table(name = "posts_table")
@Data
public class PostEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userAccountId", referencedColumnName = "id")
    private UserAccountEntity creator;

    @Column
    private String title;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "subredditId", referencedColumnName = "id")
    private SubredditEntity subreddit;

    @OneToMany(mappedBy = "post")
    private List<CommentEntity> comments;

    public PostEntity(String title, String text, UserAccountEntity creator, SubredditEntity subredditEntity) {
        this.subreddit = subredditEntity;
        this.title = title;
        this.text = text;
        this.creator = creator;
    }
}
