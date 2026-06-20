package com.fayda.health.domain.port.outbound.repository;

import com.fayda.health.domain.model.Prescription;

import java.util.List;
import java.util.UUID;

public interface PrescriptionRepository {

    Prescription save(Prescription prescription);

    List<Prescription> findByPatientId(UUID patientId);

    List<Prescription> findByHospitalId(UUID hospitalId);
}
