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
    private long commentId;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private UserAccountEntity creator;

    @OneToOne
    @JoinColumn(name = "commentId", referencedColumnName = "commentId", nullable = true)
    private CommentEntity superComment;
    @Column
    private String text;
    //ne sum reshil oshte kak da sa organizirani putishtata na file-ovet
    @Column
    private List<String> filePaths;
}
