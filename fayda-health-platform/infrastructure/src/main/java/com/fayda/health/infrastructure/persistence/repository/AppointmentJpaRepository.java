package com.fayda.health.infrastructure.persistence.repository;

import com.fayda.health.infrastructure.persistence.entity.AppointmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AppointmentJpaRepository extends JpaRepository<AppointmentJpaEntity, UUID> {
    List<AppointmentJpaEntity> findByPatientId(UUID patientId);
    List<AppointmentJpaEntity> findByDoctorId(UUID doctorId);
    List<AppointmentJpaEntity> findByHospitalId(UUID hospitalId);
}
