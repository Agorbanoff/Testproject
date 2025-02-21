package org.example.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@Table (name = "subreddit_table")
@Data
@AllArgsConstructor
public class SubredditEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subredditId;

    @Column
    private String name;

    @Column
    private String desctription;

    @JoinColumn
    private List<UserAccountEntity> users;

    @OneToMany(mappedBy = "subreddit")
    private List<PostEntity> posts;


}
