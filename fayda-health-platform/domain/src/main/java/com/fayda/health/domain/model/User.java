package com.fayda.health.domain.model;

import com.fayda.health.domain.enumtype.UserRole;
import com.fayda.health.domain.exception.DomainException;
import com.fayda.health.domain.valueobject.Email;
import com.fayda.health.domain.valueobject.FaydaId;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class User {

    private final UUID id;
    private final Email email;
    private final FaydaId faydaId;
    private final String passwordHash;
    private final String fullName;
    private final UserRole role;
    private final Instant createdAt;

    private User(UUID id, Email email, FaydaId faydaId, String passwordHash, String fullName, UserRole role, Instant createdAt) {
        if (email == null && faydaId == null) {
            throw new DomainException("User must have at least one login identity: email or Fayda ID");
        }
        this.id = Objects.requireNonNull(id);
        this.email = email;
        this.faydaId = faydaId;
        this.passwordHash = Objects.requireNonNull(passwordHash);
        this.fullName = Objects.requireNonNull(fullName);
        this.role = Objects.requireNonNull(role);
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public static User register(Email email, FaydaId faydaId, String passwordHash, String fullName, UserRole role) {
        return new User(UUID.randomUUID(), email, faydaId, passwordHash, fullName, role, Instant.now());
    }

    public static User restore(UUID id, Email email, FaydaId faydaId, String passwordHash, String fullName, UserRole role, Instant createdAt) {
        return new User(id, email, faydaId, passwordHash, fullName, role, createdAt);
    }

    public UUID id() { return id; }
    public Email email() { return email; }
    public FaydaId faydaId() { return faydaId; }
    public String passwordHash() { return passwordHash; }
    public String fullName() { return fullName; }
    public UserRole role() { return role; }
    public Instant createdAt() { return createdAt; }
}
