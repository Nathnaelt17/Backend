package com.fayda.health.domain.port.outbound.repository;

import com.fayda.health.domain.model.Patient;
import java.util.Optional;
import java.util.UUID;

public interface PatientRepository {
    /**
     * Finds a patient profile by its unique shared user ID.
     */
    Optional<Patient> findById(UUID id);

    /**
     * Persists a completely formed patient aggregate to the database.
     */
    Patient save(Patient patient);

    /**
     * Initializes an empty profile shell for a newly registered user.
     * Enforces the Shared Primary Key pattern by setting patient_id == user_id.
     *
     * @param userId The authoritative UUID generated during user creation.
     */
    void initializeProfile(UUID userId);
}