package com.tenalink.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter @Entity @Table(name = "doctors")
public class DoctorJpaEntity {
    @Id private UUID userId;
    @Column(nullable = false) private UUID hospitalId;
    @Column(nullable = false) private String specialty;
}
