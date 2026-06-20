package com.fayda.health.bootstrap.adapter.in.web;

import com.fayda.health.application.dto.BookAppointmentCommand;
import com.fayda.health.application.dto.RespondToAppointmentCommand;
import com.fayda.health.application.port.in.BookAppointmentUseCase;
import com.fayda.health.application.port.in.RespondToAppointmentUseCase;
import com.fayda.health.bootstrap.adapter.in.web.request.BookAppointmentRequest;
import com.fayda.health.bootstrap.adapter.in.web.request.RespondToAppointmentRequest;
import com.fayda.health.bootstrap.adapter.in.web.response.AppointmentResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final BookAppointmentUseCase bookAppointmentUseCase;
    private final RespondToAppointmentUseCase respondToAppointmentUseCase;

    public AppointmentController(
            BookAppointmentUseCase bookAppointmentUseCase,
            RespondToAppointmentUseCase respondToAppointmentUseCase) {
        this.bookAppointmentUseCase = bookAppointmentUseCase;
        this.respondToAppointmentUseCase = respondToAppointmentUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponse book(@Valid @RequestBody BookAppointmentRequest request) {
        return toResponse(bookAppointmentUseCase.book(new BookAppointmentCommand(
                request.patientId(), request.hospitalId(),
                request.preferredDoctorId(), request.preferredTime(),
                request.patientNotes()
        )));
    }

    @PostMapping("/{id}/approve")
    public AppointmentResponse approve(@PathVariable UUID id, @Valid @RequestBody RespondToAppointmentRequest request) {
        return toResponse(respondToAppointmentUseCase.approve(new RespondToAppointmentCommand(
                id, request.doctorId(), request.scheduledAt(), request.notes()
        )));
    }

    @PostMapping("/{id}/reject")
    public AppointmentResponse reject(@PathVariable UUID id, @Valid @RequestBody RespondToAppointmentRequest request) {
        return toResponse(respondToAppointmentUseCase.reject(new RespondToAppointmentCommand(
                id, request.doctorId(), request.scheduledAt(), request.notes()
        )));
    }

    @PostMapping("/{id}/reschedule")
    public AppointmentResponse reschedule(@PathVariable UUID id, @Valid @RequestBody RespondToAppointmentRequest request) {
        return toResponse(respondToAppointmentUseCase.reschedule(new RespondToAppointmentCommand(
                id, request.doctorId(), request.scheduledAt(), request.notes()
        )));
    }

    private AppointmentResponse toResponse(com.fayda.health.application.dto.AppointmentResult result) {
        return new AppointmentResponse(
                result.id(), result.patientId(), result.doctorId(), result.hospitalId(),
                result.requestedAt(), result.scheduledAt(), result.status(),
                result.patientNotes(), result.doctorResponseNotes()
        );
    }
}
