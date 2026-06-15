package com.tenalink.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter @Entity @Table(name = "patients")
public class PatientJpaEntity {
    @Id private UUID userId;
    private LocalDate dateOfBirth;
    private String phoneNumber;
}
