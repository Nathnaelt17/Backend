package com.fayda.health.infrastructure.persistence.adapter;

import com.fayda.health.domain.model.Appointment;
import com.fayda.health.domain.port.outbound.repository.AppointmentRepository;
import com.fayda.health.infrastructure.persistence.entity.AppointmentJpaEntity;
import com.fayda.health.infrastructure.persistence.repository.AppointmentJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AppointmentRepositoryAdapter implements AppointmentRepository {

    private final AppointmentJpaRepository jpaRepository;

    public AppointmentRepositoryAdapter(AppointmentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Appointment save(Appointment appointment) {
        return toDomain(jpaRepository.save(toJpa(appointment)));
    }

    @Override
    public Optional<Appointment> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Appointment> findByPatientId(UUID patientId) {
        return jpaRepository.findByPatientId(patientId).stream().map(this::toDomain).toList();
    }

    @Override
    public List<Appointment> findByDoctorId(UUID doctorId) {
        return jpaRepository.findByDoctorId(doctorId).stream().map(this::toDomain).toList();
    }

    @Override
    public List<Appointment> findByHospitalId(UUID hospitalId) {
        return jpaRepository.findByHospitalId(hospitalId).stream().map(this::toDomain).toList();
    }

    private AppointmentJpaEntity toJpa(Appointment appointment) {
        AppointmentJpaEntity entity = new AppointmentJpaEntity();
        entity.setId(appointment.id());
        entity.setPatientId(appointment.patientId());
        entity.setDoctorId(appointment.doctorId());
        entity.setHospitalId(appointment.hospitalId());
        entity.setRequestedAt(appointment.requestedAt());
        entity.setScheduledAt(appointment.scheduledAt());
        entity.setStatus(appointment.status());
        entity.setPatientNotes(appointment.patientNotes());
        entity.setDoctorResponseNotes(appointment.doctorResponseNotes());
        return entity;
    }

    private Appointment toDomain(AppointmentJpaEntity entity) {
        return Appointment.restore(
                entity.getId(),
                entity.getPatientId(),
                entity.getDoctorId(),
                entity.getHospitalId(),
                entity.getRequestedAt(),
                entity.getScheduledAt(),
                entity.getStatus(),
                entity.getPatientNotes(),
                entity.getDoctorResponseNotes()
        );
    }
}
