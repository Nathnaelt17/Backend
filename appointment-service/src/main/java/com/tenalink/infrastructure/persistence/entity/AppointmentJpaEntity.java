package com.tenalink.infrastructure.persistence.entity;

import com.tenalink.domain.enumtype.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter @Setter @Entity @Table(name = "appointments")
public class AppointmentJpaEntity {
    @Id private UUID id;
    @Column(nullable = false) private UUID patientId;
    @Column(nullable = false) private UUID doctorId;
    @Column(nullable = false) private UUID hospitalId;
    @Column(nullable = false) private Instant scheduledAt;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private AppointmentStatus status;
}
