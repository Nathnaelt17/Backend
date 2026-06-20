package com.fayda.health.infrastructure.persistence.entity;

import com.fayda.health.domain.enumtype.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "appointments")
public class AppointmentJpaEntity {

    @Id
    private UUID id;

    @Column(name = "patient_id", nullable = false)
    private UUID patientId;

    @Column(name = "doctor_id")
    private UUID doctorId;

    @Column(name = "hospital_id", nullable = false)
    private UUID hospitalId;

    @Column(name = "requested_at", nullable = false)
    private Instant requestedAt;

    @Column(name = "scheduled_at")
    private Instant scheduledAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private AppointmentStatus status;

    @Column(name = "patient_notes", columnDefinition = "TEXT")
    private String patientNotes;

    @Column(name = "doctor_response_notes", columnDefinition = "TEXT")
    private String doctorResponseNotes;
}
