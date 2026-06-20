package com.fayda.health.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Shared Primary Key: patient.user_id == users.id via @MapsId.
 */
@Getter
@Setter
@Entity
@Table(name = "patients")
public class PatientJpaEntity {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
}
