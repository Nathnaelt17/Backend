package com.fayda.health.infrastructure.persistence.repository;

import com.fayda.health.infrastructure.persistence.entity.MedicalEventJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MedicalEventJpaRepository extends JpaRepository<MedicalEventJpaEntity, UUID> {
    List<MedicalEventJpaEntity> findByPatientIdOrderByTimestampAsc(UUID patientId);
    List<MedicalEventJpaEntity> findByPatientIdAndHospitalIdOrderByTimestampAsc(UUID patientId, UUID hospitalId);
}
