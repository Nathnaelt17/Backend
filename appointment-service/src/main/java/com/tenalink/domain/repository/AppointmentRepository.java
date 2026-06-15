package com.tenalink.domain.repository;

import com.tenalink.domain.entity.Appointment;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);
    List<Appointment> findByPatientId(UUID patientId);
}
