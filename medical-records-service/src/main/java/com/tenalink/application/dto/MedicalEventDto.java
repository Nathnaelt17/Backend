package com.tenalink.application.dto;

import com.tenalink.domain.enumtype.EventType;

import java.time.Instant;
import java.util.UUID;

public record MedicalEventDto(UUID id, UUID patientId, UUID hospitalId, UUID authorId, Instant timestamp, EventType eventType, String eventData) {
}
