package com.tenalink.infrastructure.persistence.entity;

import com.tenalink.domain.enumtype.EventType;
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
    @Column(nullable = false)
    private UUID patientId;
    @Column(nullable = false)
    private UUID hospitalId;
    @Column(nullable = false)
    private UUID authorId;
    @Column(nullable = false)
    private Instant timestamp;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType eventType;
    @Column(nullable = false, columnDefinition = "text")
    private String eventData;
}
