package com.tenalink.application.service;

import com.tenalink.application.dto.IssuePrescriptionCommand;
import com.tenalink.application.usecase.PrescriptionUseCase;
import com.tenalink.domain.entity.Prescription;
import com.tenalink.domain.repository.PrescriptionRepository;

import java.util.List;
import java.util.UUID;

public class PrescriptionApplicationService implements PrescriptionUseCase {
    private final PrescriptionRepository repository;
    public PrescriptionApplicationService(PrescriptionRepository repository) { this.repository = repository; }
    public Prescription issue(IssuePrescriptionCommand command) { return repository.save(Prescription.issue(command.patientId(), command.doctorId(), command.hospitalId(), command.medication(), command.dosage())); }
    public List<Prescription> patientPrescriptions(UUID patientId) { return repository.findByPatientId(patientId); }
}
