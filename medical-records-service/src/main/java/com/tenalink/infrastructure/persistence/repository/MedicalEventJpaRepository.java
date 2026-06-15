package com.tenalink.infrastructure.persistence.repository;

import com.tenalink.infrastructure.persistence.entity.MedicalEventJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MedicalEventJpaRepository extends JpaRepository<MedicalEventJpaEntity, UUID> {
    List<MedicalEventJpaEntity> findByPatientIdOrderByTimestampAsc(UUID patientId);
}
