package org.example.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_account_table")
@Data
@NoArgsConstructor
public class UserAccountEntity extends BaseEntity{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sessionId", referencedColumnName = "id", nullable = true)
    private SessionEntity session;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profileId", referencedColumnName = "id" , nullable = true)
    private UserProfileEntity profile;
}