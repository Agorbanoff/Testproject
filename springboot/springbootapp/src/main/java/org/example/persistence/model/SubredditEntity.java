package org.example.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class SubredditEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long subredditId;

    @JoinColumn
    private List<UserAccountEntity> users;

    private String posts;
}
