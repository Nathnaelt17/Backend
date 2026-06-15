package com.tenalink.domain.repository;

import com.tenalink.domain.entity.MedicalEvent;

import java.util.List;
import java.util.UUID;

public interface MedicalEventRepository {
    MedicalEvent save(MedicalEvent event);
    List<MedicalEvent> findTimelineByPatientId(UUID patientId);
}
