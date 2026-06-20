package com.fayda.health.domain.model;

import com.fayda.health.domain.enumtype.AppointmentStatus;
import com.fayda.health.domain.exception.AppointmentException;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Patients own appointment requests; doctors only approve, reject, or reschedule.
 */
public class Appointment {

    private final UUID id;
    private final UUID patientId;
    private final UUID doctorId;
    private final UUID hospitalId;
    private final Instant requestedAt;
    private final Instant scheduledAt;
    private final AppointmentStatus status;
    private final String patientNotes;
    private final String doctorResponseNotes;

    private Appointment(UUID id, UUID patientId, UUID doctorId, UUID hospitalId,
                        Instant requestedAt, Instant scheduledAt, AppointmentStatus status,
                        String patientNotes, String doctorResponseNotes) {
        this.id = Objects.requireNonNull(id);
        this.patientId = Objects.requireNonNull(patientId);
        this.doctorId = doctorId;
        this.hospitalId = Objects.requireNonNull(hospitalId);
        this.requestedAt = Objects.requireNonNull(requestedAt);
        this.scheduledAt = scheduledAt;
        this.status = Objects.requireNonNull(status);
        this.patientNotes = patientNotes;
        this.doctorResponseNotes = doctorResponseNotes;
    }

    public static Appointment request(UUID patientId, UUID hospitalId, UUID preferredDoctorId,
                                      Instant preferredTime, String patientNotes) {
        return new Appointment(
                UUID.randomUUID(),
                patientId,
                preferredDoctorId,
                hospitalId,
                Instant.now(),
                preferredTime,
                AppointmentStatus.REQUESTED,
                patientNotes,
                null
        );
    }

    public static Appointment restore(UUID id, UUID patientId, UUID doctorId, UUID hospitalId,
                                      Instant requestedAt, Instant scheduledAt, AppointmentStatus status,
                                      String patientNotes, String doctorResponseNotes) {
        return new Appointment(id, patientId, doctorId, hospitalId, requestedAt, scheduledAt, status, patientNotes, doctorResponseNotes);
    }

    public Appointment approve(UUID actingDoctorId, Instant scheduledAt, String notes) {
        assertDoctorAction(actingDoctorId);
        if (status != AppointmentStatus.REQUESTED && status != AppointmentStatus.RESCHEDULED) {
            throw new AppointmentException("Only requested or rescheduled appointments can be approved");
        }
        return new Appointment(id, patientId, actingDoctorId, hospitalId, requestedAt, scheduledAt,
                AppointmentStatus.APPROVED, patientNotes, notes);
    }

    public Appointment reject(UUID actingDoctorId, String notes) {
        assertDoctorAction(actingDoctorId);
        if (status != AppointmentStatus.REQUESTED && status != AppointmentStatus.RESCHEDULED) {
            throw new AppointmentException("Only requested or rescheduled appointments can be rejected");
        }
        return new Appointment(id, patientId, actingDoctorId, hospitalId, requestedAt, scheduledAt,
                AppointmentStatus.REJECTED, patientNotes, notes);
    }

    public Appointment reschedule(UUID actingDoctorId, Instant newScheduledAt, String notes) {
        assertDoctorAction(actingDoctorId);
        if (status != AppointmentStatus.REQUESTED && status != AppointmentStatus.APPROVED) {
            throw new AppointmentException("Only requested or approved appointments can be rescheduled");
        }
        return new Appointment(id, patientId, actingDoctorId, hospitalId, requestedAt, newScheduledAt,
                AppointmentStatus.RESCHEDULED, patientNotes, notes);
    }

    private void assertDoctorAction(UUID actingDoctorId) {
        if (actingDoctorId == null) {
            throw new AppointmentException("Doctor identity is required for this action");
        }
    }

    public UUID id() { return id; }
    public UUID patientId() { return patientId; }
    public UUID doctorId() { return doctorId; }
    public UUID hospitalId() { return hospitalId; }
    public Instant requestedAt() { return requestedAt; }
    public Instant scheduledAt() { return scheduledAt; }
    public AppointmentStatus status() { return status; }
    public String patientNotes() { return patientNotes; }
    public String doctorResponseNotes() { return doctorResponseNotes; }
}
