package com.tenalink.domain.entity;

import com.tenalink.domain.enumtype.PrescriptionStatus;

import java.time.Instant;
import java.util.UUID;

public record Prescription(UUID id, UUID patientId, UUID doctorId, UUID hospitalId, String medication, String dosage, Instant issuedAt, PrescriptionStatus status) {
    public static Prescription issue(UUID patientId, UUID doctorId, UUID hospitalId, String medication, String dosage) {
        return new Prescription(UUID.randomUUID(), patientId, doctorId, hospitalId, medication, dosage, Instant.now(), PrescriptionStatus.ISSUED);
    }
}
