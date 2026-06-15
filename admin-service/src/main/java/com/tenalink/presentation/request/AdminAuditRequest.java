package com.tenalink.presentation.request;

import com.tenalink.domain.enumtype.AdminActionType;
import jakarta.validation.constraints.*;

import java.util.UUID;

public record AdminAuditRequest(@NotNull UUID userId, @NotBlank String role, @NotNull UUID hospitalId, @NotNull AdminActionType actionType, @NotNull UUID targetRecordId) {}
