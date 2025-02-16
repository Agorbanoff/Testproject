package org.example.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_table")
@Data
public class UserProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long profileId;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String pfpPath;

    @Column
    private String description;
}
