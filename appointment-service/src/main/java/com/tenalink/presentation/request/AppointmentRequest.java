package com.tenalink.presentation.request;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record AppointmentRequest(@NotNull UUID patientId, @NotNull UUID doctorId, @NotNull UUID hospitalId, @NotNull Instant scheduledAt) {}
