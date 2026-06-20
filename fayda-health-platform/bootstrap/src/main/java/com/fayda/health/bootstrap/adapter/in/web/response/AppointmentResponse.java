package com.fayda.health.bootstrap.adapter.in.web.response;

import com.fayda.health.domain.enumtype.AppointmentStatus;

import java.time.Instant;
import java.util.UUID;

public record AppointmentResponse(
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
