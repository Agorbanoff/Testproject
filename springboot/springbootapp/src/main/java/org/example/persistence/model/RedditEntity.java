package org.example.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "test_table")
@Data
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
