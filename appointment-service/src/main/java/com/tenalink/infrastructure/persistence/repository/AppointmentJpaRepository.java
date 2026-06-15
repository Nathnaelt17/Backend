package com.tenalink.infrastructure.persistence.repository;

import com.tenalink.infrastructure.persistence.entity.AppointmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AppointmentJpaRepository extends JpaRepository<AppointmentJpaEntity, UUID> {
    List<AppointmentJpaEntity> findByPatientId(UUID patientId);
}
