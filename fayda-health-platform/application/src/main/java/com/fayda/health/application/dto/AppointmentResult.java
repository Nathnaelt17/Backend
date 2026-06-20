package com.fayda.health.application.dto;

import com.fayda.health.domain.enumtype.AppointmentStatus;

import java.time.Instant;
import java.util.UUID;

public record AppointmentResult(
        UUID id,
        UUID patientId,
        UUID doctorId,
        UUID hospitalId,
        Instant requestedAt,
        Instant scheduledAt,
        AppointmentStatus status,
        String patientNotes,
        String doctorResponseNotes
) {}
