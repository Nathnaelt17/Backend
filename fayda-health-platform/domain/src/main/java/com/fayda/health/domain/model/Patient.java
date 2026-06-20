package com.fayda.health.domain.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Shared-primary-key aggregate: patient_id == user_id.
 */
public class Patient {

    private final UUID id;
    private final LocalDate dateOfBirth;
    private final String phoneNumber;

    public Patient(UUID id, LocalDate dateOfBirth, String phoneNumber) {
        this.id = Objects.requireNonNull(id);
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }

    public static Patient create(UUID userId, LocalDate dateOfBirth, String phoneNumber) {
        return new Patient(userId, dateOfBirth, phoneNumber);
    }

    public UUID id() { return id; }
    public LocalDate dateOfBirth() { return dateOfBirth; }
    public String phoneNumber() { return phoneNumber; }
}
