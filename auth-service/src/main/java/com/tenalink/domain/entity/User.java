package com.tenalink.domain.entity;

import com.tenalink.domain.enumtype.Role;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class User {
    private final UUID id;
    private final String email;
    private final String passwordHash;
    private final String fullName;
    private final Role role;
    private final Instant createdAt;

    private User(UUID id, String email, String passwordHash, String fullName, Role role, Instant createdAt) {
        this.id = Objects.requireNonNull(id);
        this.email = Objects.requireNonNull(email).toLowerCase();
        this.passwordHash = Objects.requireNonNull(passwordHash);
        this.fullName = Objects.requireNonNull(fullName);
        this.role = Objects.requireNonNull(role);
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public static User register(String email, String passwordHash, String fullName, Role role) {
        return new User(UUID.randomUUID(), email, passwordHash, fullName, role, Instant.now());
    }

    public static User restore(UUID id, String email, String passwordHash, String fullName, Role role, Instant createdAt) {
        return new User(id, email, passwordHash, fullName, role, createdAt);
    }

    public UUID id() { return id; }
    public String email() { return email; }
    public String passwordHash() { return passwordHash; }
    public String fullName() { return fullName; }
    public Role role() { return role; }
    public Instant createdAt() { return createdAt; }
}
