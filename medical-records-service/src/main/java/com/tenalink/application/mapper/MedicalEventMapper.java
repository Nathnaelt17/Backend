package com.tenalink.application.mapper;

import com.tenalink.application.dto.MedicalEventDto;
import com.tenalink.domain.entity.MedicalEvent;

public class MedicalEventMapper {
    public MedicalEventDto toDto(MedicalEvent event) {
        return new MedicalEventDto(event.id(), event.patientId(), event.hospitalId(), event.authorId(), event.timestamp(), event.eventType(), event.eventData());
    }
}
