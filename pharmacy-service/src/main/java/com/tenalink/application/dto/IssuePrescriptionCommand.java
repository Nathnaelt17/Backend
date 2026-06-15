package com.tenalink.application.dto;

import java.util.UUID;

public record IssuePrescriptionCommand(UUID patientId, UUID doctorId, UUID hospitalId, String medication, String dosage) {}
