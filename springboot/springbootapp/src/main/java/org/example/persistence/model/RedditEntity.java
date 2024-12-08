package org.example.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "test_table")
public class RedditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String password;

}
