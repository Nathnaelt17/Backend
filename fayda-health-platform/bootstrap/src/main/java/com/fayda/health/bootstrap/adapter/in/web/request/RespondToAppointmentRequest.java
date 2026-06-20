package com.fayda.health.bootstrap.adapter.in.web.request;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record RespondToAppointmentRequest(
        @NotNull UUID doctorId,
        Instant scheduledAt,
        String notes
) {}
