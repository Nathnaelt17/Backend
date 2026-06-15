package com.tenalink.presentation.response;

import com.tenalink.domain.enumtype.EventType;

import java.time.Instant;
import java.util.UUID;

public record MedicalEventResponse(UUID id, UUID patientId, UUID hospitalId, UUID authorId, Instant timestamp, EventType eventType, String eventData) {
}
