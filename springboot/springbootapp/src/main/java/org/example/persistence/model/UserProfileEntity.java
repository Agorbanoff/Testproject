package org.example.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_profile_table")
@Data
@NoArgsConstructor
public class UserProfileEntity extends BaseEntity{

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String pfpPath;

    @Column
    private String description;
}
