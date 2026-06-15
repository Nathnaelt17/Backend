package com.tenalink.presentation.request;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record PrescriptionRequest(@NotNull UUID patientId, @NotNull UUID doctorId, @NotNull UUID hospitalId, @NotBlank String medication, @NotBlank String dosage) {}
