package com.tenalink.presentation.response;

import com.tenalink.domain.enumtype.PrescriptionStatus;

import java.time.Instant;
import java.util.UUID;

public record PrescriptionResponse(UUID id, UUID patientId, UUID doctorId, UUID hospitalId, String medication, String dosage, Instant issuedAt, PrescriptionStatus status) {}
