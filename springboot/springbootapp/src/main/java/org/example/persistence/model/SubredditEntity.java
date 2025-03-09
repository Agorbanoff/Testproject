package org.example.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table (name = "subreddit_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubredditEntity extends BaseEntity{
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


    @OneToMany(mappedBy = "subreddit", cascade = CascadeType.ALL)
    private List<PostEntity> posts;
}
