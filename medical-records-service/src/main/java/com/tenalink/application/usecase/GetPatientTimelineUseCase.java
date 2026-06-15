package com.tenalink.application.usecase;

import com.tenalink.application.dto.MedicalEventDto;

import java.util.List;
import java.util.UUID;

public interface GetPatientTimelineUseCase {
    List<MedicalEventDto> timeline(UUID patientId);
}
