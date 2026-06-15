package com.tenalink.presentation.request;

import com.tenalink.domain.enumtype.EventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AppendMedicalEventRequest(
        @NotNull UUID patientId,
        @NotNull UUID hospitalId,
        @NotNull EventType eventType,
        @NotBlank String eventData
) {
}
