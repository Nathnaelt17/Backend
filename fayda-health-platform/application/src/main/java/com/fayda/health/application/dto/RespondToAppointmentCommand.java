package com.fayda.health.application.dto;

import com.fayda.health.domain.enumtype.AppointmentStatus;

import java.time.Instant;
import java.util.UUID;

public record RespondToAppointmentCommand(
        UUID appointmentId,
        UUID doctorId,
        Instant scheduledAt,
        String notes
) {}
