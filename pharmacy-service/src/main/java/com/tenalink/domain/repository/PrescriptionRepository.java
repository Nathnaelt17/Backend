package com.tenalink.domain.repository;

import com.tenalink.domain.entity.Prescription;

import java.util.List;
import java.util.UUID;

public interface PrescriptionRepository {
    Prescription save(Prescription prescription);
    List<Prescription> findByPatientId(UUID patientId);
}
