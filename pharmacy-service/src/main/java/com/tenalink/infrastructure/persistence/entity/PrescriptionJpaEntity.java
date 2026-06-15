package com.tenalink.infrastructure.persistence.entity;

import com.tenalink.domain.enumtype.PrescriptionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter @Setter @Entity @Table(name = "prescriptions")
public class PrescriptionJpaEntity {
    @Id private UUID id;
    @Column(nullable = false) private UUID patientId;
    @Column(nullable = false) private UUID doctorId;
    @Column(nullable = false) private UUID hospitalId;
    @Column(nullable = false) private String medication;
    @Column(nullable = false) private String dosage;
    @Column(nullable = false) private Instant issuedAt;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private PrescriptionStatus status;
}
