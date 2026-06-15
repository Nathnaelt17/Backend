package com.tenalink.application.dto;

import java.time.Instant;
import java.util.UUID;

public record AppointmentCommand(UUID patientId, UUID doctorId, UUID hospitalId, Instant scheduledAt) {
}
