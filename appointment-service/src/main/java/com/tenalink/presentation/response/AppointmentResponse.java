package com.tenalink.presentation.response;

import com.tenalink.domain.enumtype.AppointmentStatus;

import java.time.Instant;
import java.util.UUID;

public record AppointmentResponse(UUID id, UUID patientId, UUID doctorId, UUID hospitalId, Instant scheduledAt, AppointmentStatus status) {}
