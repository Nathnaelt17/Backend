package com.fayda.health.infrastructure.persistence.entity;

import com.fayda.health.domain.enumtype.EventType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "medical_events")
public class MedicalEventJpaEntity {

    @Id
    private UUID id;

    @Column(name = "patient_id", nullable = false)
    private UUID patientId;

    @Column(name = "hospital_id", nullable = false)
    private UUID hospitalId;

    @Column(name = "doctor_id", nullable = false)
    private UUID doctorId;

    @Column(nullable = false)
    private Instant timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false, length = 64)
    private EventType eventType;

    @Column(name = "event_data", nullable = false, columnDefinition = "TEXT")
    private String eventData;
}
