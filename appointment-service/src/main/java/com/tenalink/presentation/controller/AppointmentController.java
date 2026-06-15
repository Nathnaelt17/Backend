package com.tenalink.presentation.controller;

import com.tenalink.application.dto.AppointmentCommand;
import com.tenalink.application.usecase.AppointmentUseCase;
import com.tenalink.domain.entity.Appointment;
import com.tenalink.presentation.request.AppointmentRequest;
import com.tenalink.presentation.response.AppointmentResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {
    private final AppointmentUseCase useCase;
    public AppointmentController(AppointmentUseCase useCase) { this.useCase = useCase; }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) AppointmentResponse schedule(@Valid @RequestBody AppointmentRequest r) { return toResponse(useCase.schedule(new AppointmentCommand(r.patientId(), r.doctorId(), r.hospitalId(), r.scheduledAt()))); }
    @GetMapping("/patients/{patientId}") List<AppointmentResponse> byPatient(@PathVariable UUID patientId) { return useCase.patientAppointments(patientId).stream().map(this::toResponse).toList(); }
    private AppointmentResponse toResponse(Appointment a) { return new AppointmentResponse(a.id(), a.patientId(), a.doctorId(), a.hospitalId(), a.scheduledAt(), a.status()); }
}
