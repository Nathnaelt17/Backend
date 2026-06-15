package com.tenalink.application.usecase;

import com.tenalink.application.dto.AppendMedicalEventCommand;
import com.tenalink.application.dto.MedicalEventDto;

public interface AppendMedicalEventUseCase {
    MedicalEventDto append(AppendMedicalEventCommand command);
}
