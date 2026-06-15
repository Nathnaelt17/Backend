package com.tenalink.application.service;

import com.tenalink.application.dto.AppendMedicalEventCommand;
import com.tenalink.application.dto.MedicalEventDto;
import com.tenalink.application.mapper.MedicalEventMapper;
import com.tenalink.application.usecase.AppendMedicalEventUseCase;
import com.tenalink.application.usecase.GetPatientTimelineUseCase;
import com.tenalink.domain.entity.AuditLog;
import com.tenalink.domain.entity.MedicalEvent;
import com.tenalink.domain.repository.AuditLogRepository;
import com.tenalink.domain.repository.MedicalEventRepository;

import java.util.List;
import java.util.UUID;

public class MedicalTimelineService implements AppendMedicalEventUseCase, GetPatientTimelineUseCase {
    private final MedicalEventRepository medicalEventRepository;
    private final AuditLogRepository auditLogRepository;
    private final MedicalEventMapper mapper;

    public MedicalTimelineService(MedicalEventRepository medicalEventRepository, AuditLogRepository auditLogRepository, MedicalEventMapper mapper) {
        this.medicalEventRepository = medicalEventRepository;
        this.auditLogRepository = auditLogRepository;
        this.mapper = mapper;
    }

    @Override
    public MedicalEventDto append(AppendMedicalEventCommand command) {
        MedicalEvent event = MedicalEvent.append(command.patientId(), command.hospitalId(), command.authorId(), command.eventType(), command.eventData());
        MedicalEvent saved = medicalEventRepository.save(event);
        auditLogRepository.save(AuditLog.record(command.authorId(), command.role(), command.hospitalId(), "MEDICAL_EVENT_APPENDED", saved.id()));
        return mapper.toDto(saved);
    }

    @Override
    public List<MedicalEventDto> timeline(UUID patientId) {
        return medicalEventRepository.findTimelineByPatientId(patientId).stream().map(mapper::toDto).toList();
    }
}
