package com.tenalink.presentation.controller;

import com.tenalink.application.dto.AppendMedicalEventCommand;
import com.tenalink.application.dto.MedicalEventDto;
import com.tenalink.application.usecase.AppendMedicalEventUseCase;
import com.tenalink.application.usecase.GetPatientTimelineUseCase;
import com.tenalink.presentation.request.AppendMedicalEventRequest;
import com.tenalink.presentation.response.MedicalEventResponse;
import com.tenalink.security.GatewayPrincipal;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/records")
public class MedicalRecordController {
    private final AppendMedicalEventUseCase appendMedicalEventUseCase;
    private final GetPatientTimelineUseCase getPatientTimelineUseCase;

    public MedicalRecordController(AppendMedicalEventUseCase appendMedicalEventUseCase, GetPatientTimelineUseCase getPatientTimelineUseCase) {
        this.appendMedicalEventUseCase = appendMedicalEventUseCase;
        this.getPatientTimelineUseCase = getPatientTimelineUseCase;
    }

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalEventResponse append(@AuthenticationPrincipal GatewayPrincipal principal,
                                       @Valid @RequestBody AppendMedicalEventRequest request) {
        return toResponse(appendMedicalEventUseCase.append(new AppendMedicalEventCommand(
                request.patientId(),
                request.hospitalId(),
                principal.userId(),
                request.eventType(),
                request.eventData(),
                principal.primaryRole()
        )));
    }

    @GetMapping("/patients/{patientId}/timeline")
    public List<MedicalEventResponse> timeline(@PathVariable UUID patientId) {
        return getPatientTimelineUseCase.timeline(patientId).stream().map(this::toResponse).toList();
    }

    private MedicalEventResponse toResponse(MedicalEventDto dto) {
        return new MedicalEventResponse(dto.id(), dto.patientId(), dto.hospitalId(), dto.authorId(), dto.timestamp(), dto.eventType(), dto.eventData());
    }
}
