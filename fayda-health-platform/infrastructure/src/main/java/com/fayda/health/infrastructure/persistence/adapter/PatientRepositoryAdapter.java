// PATH: fayda-health-platform/infrastructure/src/main/java/com/fayda/health/infrastructure/persistence/adapter/PatientRepositoryAdapter.java
package com.fayda.health.infrastructure.persistence.adapter;

import com.fayda.health.domain.model.Patient;
import com.fayda.health.domain.port.outbound.repository.PatientRepository;
import com.fayda.health.infrastructure.persistence.entity.PatientJpaEntity;
import com.fayda.health.infrastructure.persistence.entity.UserJpaEntity;
import com.fayda.health.infrastructure.persistence.repository.PatientJpaRepository;
import com.fayda.health.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PatientRepositoryAdapter implements PatientRepository {

    private final PatientJpaRepository patientJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public PatientRepositoryAdapter(PatientJpaRepository patientJpaRepository, UserJpaRepository userJpaRepository) {
        this.patientJpaRepository = patientJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<Patient> findById(UUID id) {
        return patientJpaRepository.findById(id).map(entity -> new Patient(entity.getUserId(), null, null));
    }

    @Override
    public Patient save(Patient patient) {
        PatientJpaEntity entity = new PatientJpaEntity();
        entity.setUserId(patient.id());
        PatientJpaEntity saved = patientJpaRepository.save(entity);
        return new Patient(saved.getUserId(), null, null);
    }

    @Override
    public void initializeProfile(UUID userId) {
        UserJpaEntity userRef = userJpaRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Parent User profile entity not found."));
        
        PatientJpaEntity patientEntity = new PatientJpaEntity();
        patientEntity.setUser(userRef); // Anchors relationship using Hibernate's @MapsId mapping logic
        
        patientJpaRepository.save(patientEntity);
    }
}