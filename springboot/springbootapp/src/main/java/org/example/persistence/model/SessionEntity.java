package org.example.persistence.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_session_table")
@Data
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = false, nullable = false)
    private String password;
}
