package com.tenalink.domain.entity;

import com.tenalink.domain.enumtype.AdminActionType;

import java.time.Instant;
import java.util.UUID;

public record AuditLog(UUID id, UUID userId, String role, UUID hospitalId, Instant timestamp, AdminActionType actionType, UUID targetRecordId) {
    public static AuditLog record(UUID userId, String role, UUID hospitalId, AdminActionType actionType, UUID targetRecordId) {
        return new AuditLog(UUID.randomUUID(), userId, role, hospitalId, Instant.now(), actionType, targetRecordId);
    }
}
