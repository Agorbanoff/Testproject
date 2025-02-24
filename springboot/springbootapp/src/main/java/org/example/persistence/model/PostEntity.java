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
    private Long id;

    @OneToOne
    @JoinColumn(name = "userAccountId", referencedColumnName = "id")
    private UserAccountEntity creator;

    @Column
    private String title;

    @Column
    private String text;

    @Column
    private String subredditName;

    @OneToMany(mappedBy = "post")
    private List<CommentEntity> comments;

    public PostEntity(String title, String text, UserAccountEntity creator, String subredditName) {
        this.subredditName = subredditName;
        this.title = title;
        this.text = text;
        this.creator = creator;
    }
}
