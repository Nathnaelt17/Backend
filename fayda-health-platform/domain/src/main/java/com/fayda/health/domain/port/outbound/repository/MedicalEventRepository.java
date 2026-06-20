package com.fayda.health.domain.port.outbound.repository;

import com.fayda.health.domain.model.MedicalEvent;

import java.util.List;
import java.util.UUID;

public interface MedicalEventRepository {

    MedicalEvent append(MedicalEvent event);

    List<MedicalEvent> findByPatientId(UUID patientId);

    List<MedicalEvent> findByPatientIdAndHospitalId(UUID patientId, UUID hospitalId);
}
