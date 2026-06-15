package com.tenalink.domain.entity;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class AuditLog {
    private final UUID id;
    private final UUID userId;
    private final String role;
    private final UUID hospitalId;
    private final Instant timestamp;
    private final String actionType;
    private final UUID targetRecordId;

    private AuditLog(UUID id, UUID userId, String role, UUID hospitalId, Instant timestamp, String actionType, UUID targetRecordId) {
        this.id = Objects.requireNonNull(id);
        this.userId = Objects.requireNonNull(userId);
        this.role = Objects.requireNonNull(role);
        this.hospitalId = Objects.requireNonNull(hospitalId);
        this.timestamp = Objects.requireNonNull(timestamp);
        this.actionType = Objects.requireNonNull(actionType);
        this.targetRecordId = Objects.requireNonNull(targetRecordId);
    }

    public static AuditLog record(UUID userId, String role, UUID hospitalId, String actionType, UUID targetRecordId) {
        return new AuditLog(UUID.randomUUID(), userId, role, hospitalId, Instant.now(), actionType, targetRecordId);
    }

    public static AuditLog restore(UUID id, UUID userId, String role, UUID hospitalId, Instant timestamp, String actionType, UUID targetRecordId) {
        return new AuditLog(id, userId, role, hospitalId, timestamp, actionType, targetRecordId);
    }

    public UUID id() { return id; }
    public UUID userId() { return userId; }
    public String role() { return role; }
    public UUID hospitalId() { return hospitalId; }
    public Instant timestamp() { return timestamp; }
    public String actionType() { return actionType; }
    public UUID targetRecordId() { return targetRecordId; }
}
