package org.example.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

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

    @ManyToMany()
    @JoinTable(
                name = "subreddit_users_table",
                joinColumns = @JoinColumn(name = "subredditId"),
                inverseJoinColumns = @JoinColumn(name = "userId")
                )

    private Set<UserAccountEntity> users;

//    @OneToMany(mappedBy = "subreddit")
//    private List<PostEntity> posts;
}
