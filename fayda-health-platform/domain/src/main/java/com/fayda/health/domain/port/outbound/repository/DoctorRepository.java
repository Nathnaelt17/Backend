// PATH: domain/src/main/java/com/fayda/health/domain/port/outbound/repository/DoctorRepository.java
package com.fayda.health.domain.port.outbound.repository;

import com.fayda.health.domain.model.Doctor;
import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository {
    /**
     * Finds a doctor profile by its unique shared user ID.
     */
    Optional<Doctor> findById(UUID id);

    /**
     * Persists a completely formed doctor aggregate to the database.
     */
    Doctor save(Doctor doctor);

    /**
     * Initializes an empty profile shell for a newly registered user.
     * Enforces the Shared Primary Key pattern by setting doctor_id == user_id.
     *
     * @param userId The authoritative UUID generated during user creation.
     */
    void initializeProfile(UUID userId);
}