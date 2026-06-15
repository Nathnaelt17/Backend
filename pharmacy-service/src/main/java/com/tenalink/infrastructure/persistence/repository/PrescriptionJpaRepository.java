package com.tenalink.infrastructure.persistence.repository;

import com.tenalink.infrastructure.persistence.entity.PrescriptionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PrescriptionJpaRepository extends JpaRepository<PrescriptionJpaEntity, UUID> {
    List<PrescriptionJpaEntity> findByPatientId(UUID patientId);
}
