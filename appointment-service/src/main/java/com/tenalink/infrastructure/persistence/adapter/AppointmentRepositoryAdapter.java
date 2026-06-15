package com.tenalink.infrastructure.persistence.adapter;

import com.tenalink.common.identity.DoctorRef;
import com.tenalink.common.identity.HospitalRef;
import com.tenalink.common.identity.PatientRef;
import com.tenalink.domain.entity.Appointment;
import com.tenalink.domain.repository.AppointmentRepository;
import com.tenalink.infrastructure.persistence.entity.AppointmentJpaEntity;
import com.tenalink.infrastructure.persistence.repository.AppointmentJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AppointmentRepositoryAdapter implements AppointmentRepository {
    private final AppointmentJpaRepository jpaRepository;

    public AppointmentRepositoryAdapter(AppointmentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public Appointment save(Appointment appointment) {
        return toDomain(jpaRepository.save(toJpa(appointment)));
    }

    public List<Appointment> findByPatientId(UUID patientId) {
        return jpaRepository.findByPatientId(patientId).stream().map(this::toDomain).toList();
    }

    private AppointmentJpaEntity toJpa(Appointment appointment) {
        AppointmentJpaEntity entity = new AppointmentJpaEntity();
        entity.setId(appointment.id());
        entity.setPatientId(appointment.patientId());
        entity.setDoctorId(appointment.doctorId());
        entity.setHospitalId(appointment.hospitalId());
        entity.setScheduledAt(appointment.scheduledAt());
        entity.setStatus(appointment.status());
        return entity;
    }

    private Appointment toDomain(AppointmentJpaEntity entity) {
        return new Appointment(
                entity.getId(),
                new PatientRef(entity.getPatientId()),
                new DoctorRef(entity.getDoctorId()),
                new HospitalRef(entity.getHospitalId()),
                entity.getScheduledAt(),
                entity.getStatus()
        );
    }
}
