package org.example.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Table(name = "comments_table")
@Data
public class CommentEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private UserAccountEntity creator;

    @ManyToOne
    @JoinColumn(name = "postId", referencedColumnName = "id", nullable = false)
    private PostEntity post;

    @OneToOne
    @JoinColumn(name = "commentId", referencedColumnName = "id", nullable = true)
    private CommentEntity upperComment;

    @Column
    private String text;

    public CommentEntity(UserAccountEntity creator, String text, PostEntity post, CommentEntity upperComment){
        this.creator = creator;
        this.text = text;
        this.post = post;
        this.upperComment = upperComment;
    }
    //ne sum reshil oshte kak da sa organizirani putishtata na file-ovetе //typo
}
