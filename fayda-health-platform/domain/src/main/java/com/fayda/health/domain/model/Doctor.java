package com.fayda.health.domain.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Shared-primary-key aggregate: doctor_id == user_id.
 */
public class Doctor {

    private final UUID id;
    private final UUID hospitalId;
    private final String specialty;
    private final String licenseNumber;

    public Doctor(UUID id, UUID hospitalId, String specialty, String licenseNumber) {
        this.id = Objects.requireNonNull(id);
        this.hospitalId = Objects.requireNonNull(hospitalId);
        this.specialty = Objects.requireNonNull(specialty);
        this.licenseNumber = Objects.requireNonNull(licenseNumber);
    }

    public static Doctor create(UUID userId, UUID hospitalId, String specialty, String licenseNumber) {
        return new Doctor(userId, hospitalId, specialty, licenseNumber);
    }

    public UUID id() { return id; }
    public UUID hospitalId() { return hospitalId; }
    public String specialty() { return specialty; }
    public String licenseNumber() { return licenseNumber; }
}
