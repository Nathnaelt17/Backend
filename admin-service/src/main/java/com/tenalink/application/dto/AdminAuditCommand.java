package com.tenalink.application.dto;

import com.tenalink.domain.enumtype.AdminActionType;

import java.util.UUID;

public record AdminAuditCommand(UUID userId, String role, UUID hospitalId, AdminActionType actionType, UUID targetRecordId) {}
