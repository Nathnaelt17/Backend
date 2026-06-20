package com.fayda.health.infrastructure.persistence.repository;

import com.fayda.health.infrastructure.persistence.entity.PrescriptionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PrescriptionJpaRepository extends JpaRepository<PrescriptionJpaEntity, UUID> {
    List<PrescriptionJpaEntity> findByPatientId(UUID patientId);
    List<PrescriptionJpaEntity> findByHospitalId(UUID hospitalId);
}
