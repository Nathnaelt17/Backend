package com.tenalink.application.usecase;

import com.tenalink.application.dto.IssuePrescriptionCommand;
import com.tenalink.domain.entity.Prescription;

import java.util.List;
import java.util.UUID;

public interface PrescriptionUseCase {
    Prescription issue(IssuePrescriptionCommand command);
    List<Prescription> patientPrescriptions(UUID patientId);
}
