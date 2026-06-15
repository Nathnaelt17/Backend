package com.tenalink.domain.entity;

import com.tenalink.common.identity.DoctorRef;
import com.tenalink.common.identity.HospitalRef;
import com.tenalink.common.identity.PatientRef;
import com.tenalink.domain.enumtype.AppointmentStatus;

import java.time.Instant;
import java.util.UUID;

public record Appointment(
        UUID id,
        PatientRef patient,
        DoctorRef doctor,
        HospitalRef hospital,
        Instant scheduledAt,
        AppointmentStatus status
) {
    public static Appointment schedule(PatientRef patient, DoctorRef doctor, HospitalRef hospital, Instant scheduledAt) {
        return new Appointment(UUID.randomUUID(), patient, doctor, hospital, scheduledAt, AppointmentStatus.SCHEDULED);
    }

    public UUID patientId() {
        return patient.value();
    }

    public UUID doctorId() {
        return doctor.value();
    }

    public UUID hospitalId() {
        return hospital.value();
    }
}
