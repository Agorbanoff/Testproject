package org.example.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_account_table")
@Data
public class UserAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long userId;

    @OneToOne
    @JoinColumn(name = "sessionId", referencedColumnName = "sessionId", nullable = true)
    private SessionEntity session;

    @OneToOne
    @JoinColumn(name = "profileId", referencedColumnName = "profileId" , nullable = true)
    private UserProfileEntity profile;
}
