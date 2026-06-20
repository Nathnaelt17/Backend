package com.fayda.health.application.dto;

import java.time.Instant;
import java.util.UUID;

public record BookAppointmentCommand(
        UUID patientId,
        UUID hospitalId,
        UUID preferredDoctorId,
        Instant preferredTime,
        String patientNotes
) {}
