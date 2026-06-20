package com.fayda.health.bootstrap.adapter.in.web.request;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record BookAppointmentRequest(
        @NotNull UUID patientId,
        @NotNull UUID hospitalId,
        UUID preferredDoctorId,
        Instant preferredTime,
        String patientNotes
) {}
