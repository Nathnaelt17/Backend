package com.fayda.health.domain.model;

import com.fayda.health.domain.enumtype.EventType;
import com.fayda.health.domain.exception.ImmutableTimelineException;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Append-only medical timeline event, anchored to a hospital facility.
 * The writing clinician is tracked as {@code doctorId} (shared PK with users/doctors).
 */
public class MedicalEvent {

    private final UUID id;
    private final UUID patientId;
    private final UUID hospitalId;
    private final UUID doctorId;
    private final Instant timestamp;
    private final EventType eventType;
    private final String eventData;

    private MedicalEvent(UUID id, UUID patientId, UUID hospitalId, UUID doctorId,
                         Instant timestamp, EventType eventType, String eventData) {
        this.id = Objects.requireNonNull(id);
        this.patientId = Objects.requireNonNull(patientId);
        this.hospitalId = Objects.requireNonNull(hospitalId);
        this.doctorId = Objects.requireNonNull(doctorId);
        this.timestamp = Objects.requireNonNull(timestamp);
        this.eventType = Objects.requireNonNull(eventType);
        if (eventData == null || eventData.isBlank()) {
            throw new ImmutableTimelineException("Medical event data is required");
        }
        this.eventData = eventData;
    }

    public static MedicalEvent append(UUID patientId, UUID hospitalId, UUID doctorId,
                                      EventType eventType, String eventData) {
        return new MedicalEvent(UUID.randomUUID(), patientId, hospitalId, doctorId,
                Instant.now(), eventType, eventData);
    }

    public static MedicalEvent restore(UUID id, UUID patientId, UUID hospitalId, UUID doctorId,
                                       Instant timestamp, EventType eventType, String eventData) {
        return new MedicalEvent(id, patientId, hospitalId, doctorId, timestamp, eventType, eventData);
    }

    public void rejectMutation() {
        throw new ImmutableTimelineException("Medical events are append-only and cannot be modified");
    }

    public UUID id() { return id; }
    public UUID patientId() { return patientId; }
    public UUID hospitalId() { return hospitalId; }
    public UUID doctorId() { return doctorId; }
    public Instant timestamp() { return timestamp; }
    public EventType eventType() { return eventType; }
    public String eventData() { return eventData; }
}
