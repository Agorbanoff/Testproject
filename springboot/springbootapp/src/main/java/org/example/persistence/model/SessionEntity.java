package org.example.persistence.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_session_table")
@Data
@NoArgsConstructor
public class SessionEntity extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String sessionString;

    @Column(nullable = false)
    private Long expirationDateInMillis;

    @Column(nullable = false)
    private boolean isExpired;

    public SessionEntity(String sessionString, long expirationDateInMillis) {
        this.id = null;
        this.sessionString = sessionString;
        this.expirationDateInMillis = expirationDateInMillis;
        this.isExpired = false;
    }

}
