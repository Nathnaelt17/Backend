package com.fayda.health.infrastructure.persistence.entity;

import com.fayda.health.domain.enumtype.PrescriptionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "prescriptions")
public class PrescriptionJpaEntity {

    @Id
    private UUID id;

    @Column(name = "patient_id", nullable = false)
    private UUID patientId;

    @Column(name = "doctor_id", nullable = false)
    private UUID doctorId;

    @Column(name = "hospital_id", nullable = false)
    private UUID hospitalId;

    @Column(nullable = false)
    private String medication;

    @Column(nullable = false)
    private String dosage;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private PrescriptionStatus status;

    @Column(name = "issued_at", nullable = false)
    private Instant issuedAt;
}
