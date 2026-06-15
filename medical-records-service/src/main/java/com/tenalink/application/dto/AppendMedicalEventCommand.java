package com.tenalink.application.dto;

import com.tenalink.domain.enumtype.EventType;

import java.util.UUID;

public record AppendMedicalEventCommand(UUID patientId, UUID hospitalId, UUID authorId, EventType eventType, String eventData, String role) {
}
