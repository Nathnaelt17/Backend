package com.fayda.health.infrastructure.persistence.entity;

import com.fayda.health.domain.enumtype.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserJpaEntity {

    @Id
    private UUID id;

    @Column(unique = true)
    private String email;

    @Column(name = "fayda_id", unique = true, length = 12)
    private String faydaId;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private UserRole role;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
}
