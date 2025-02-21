package org.example.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_profile_table")
@Data
public class UserProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String pfpPath;

    @Column
    private String description;
}
