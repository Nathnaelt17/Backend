package com.fayda.health.domain.model;

import com.fayda.health.domain.enumtype.PrescriptionStatus;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Prescription {

    private final UUID id;
    private final UUID patientId;
    private final UUID doctorId;
    private final UUID hospitalId;
    private final String medication;
    private final String dosage;
    private final String instructions;
    private final PrescriptionStatus status;
    private final Instant issuedAt;

    public Prescription(UUID id, UUID patientId, UUID doctorId, UUID hospitalId,
                        String medication, String dosage, String instructions,
                        PrescriptionStatus status, Instant issuedAt) {
        this.id = Objects.requireNonNull(id);
        this.patientId = Objects.requireNonNull(patientId);
        this.doctorId = Objects.requireNonNull(doctorId);
        this.hospitalId = Objects.requireNonNull(hospitalId);
        this.medication = Objects.requireNonNull(medication);
        this.dosage = Objects.requireNonNull(dosage);
        this.instructions = instructions;
        this.status = Objects.requireNonNull(status);
        this.issuedAt = Objects.requireNonNull(issuedAt);
    }

    public static Prescription issue(UUID patientId, UUID doctorId, UUID hospitalId,
                                     String medication, String dosage, String instructions) {
        return new Prescription(
                UUID.randomUUID(), patientId, doctorId, hospitalId,
                medication, dosage, instructions, PrescriptionStatus.ACTIVE, Instant.now()
        );
    }

    public UUID id() { return id; }
    public UUID patientId() { return patientId; }
    public UUID doctorId() { return doctorId; }
    public UUID hospitalId() { return hospitalId; }
    public String medication() { return medication; }
    public String dosage() { return dosage; }
    public String instructions() { return instructions; }
    public PrescriptionStatus status() { return status; }
    public Instant issuedAt() { return issuedAt; }
}
