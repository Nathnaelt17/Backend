// PATH: fayda-health-platform/infrastructure/src/main/java/com/fayda/health/infrastructure/persistence/adapter/DoctorRepositoryAdapter.java
package com.fayda.health.infrastructure.persistence.adapter;

import com.fayda.health.domain.model.Doctor;
import com.fayda.health.domain.port.outbound.repository.DoctorRepository;
import com.fayda.health.infrastructure.persistence.entity.DoctorJpaEntity;
import com.fayda.health.infrastructure.persistence.entity.UserJpaEntity;
import com.fayda.health.infrastructure.persistence.repository.DoctorJpaRepository;
import com.fayda.health.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class DoctorRepositoryAdapter implements DoctorRepository {

    private final DoctorJpaRepository doctorJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public DoctorRepositoryAdapter(DoctorJpaRepository doctorJpaRepository, UserJpaRepository userJpaRepository) {
        this.doctorJpaRepository = doctorJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<Doctor> findById(UUID id) {
        return doctorJpaRepository.findById(id).map(entity -> new Doctor(entity.getUserId(), null, null, null));
    }

    @Override
    public Doctor save(Doctor doctor) {
        DoctorJpaEntity entity = new DoctorJpaEntity();
        entity.setUserId(doctor.id());
        DoctorJpaEntity saved = doctorJpaRepository.save(entity);
        return new Doctor(saved.getUserId(), null, null, null);
    }

    @Override
    public void initializeProfile(UUID userId) {
        UserJpaEntity userRef = userJpaRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Parent User profile entity not found."));
        
        DoctorJpaEntity doctorEntity = new DoctorJpaEntity();
        doctorEntity.setUser(userRef); // Anchors relational shared PK link
        
        // Use a standard fallback UUID for unassigned facilities during shell initialization 
        // until the explicit verification onboarding flow completes.
        doctorEntity.setHospitalId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        doctorEntity.setSpecialty("UNASSIGNED");
        doctorEntity.setLicenseNumber("PENDING-" + userId);
        
        doctorJpaRepository.save(doctorEntity);
    }
}