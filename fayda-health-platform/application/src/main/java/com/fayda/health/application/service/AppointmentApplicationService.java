package com.fayda.health.application.service;

import com.fayda.health.application.dto.AppointmentResult;
import com.fayda.health.application.dto.BookAppointmentCommand;
import com.fayda.health.application.dto.RespondToAppointmentCommand;
import com.fayda.health.application.port.in.BookAppointmentUseCase;
import com.fayda.health.application.port.in.RespondToAppointmentUseCase;
import com.fayda.health.domain.exception.AppointmentException;
import com.fayda.health.domain.model.Appointment;
import com.fayda.health.domain.port.outbound.repository.AppointmentRepository;
import com.fayda.health.domain.port.outbound.repository.HospitalRepository;
import com.fayda.health.domain.port.outbound.repository.PatientRepository;

public class AppointmentApplicationService implements BookAppointmentUseCase, RespondToAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    public AppointmentApplicationService(
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            HospitalRepository hospitalRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public AppointmentResult book(BookAppointmentCommand command) {
        patientRepository.findById(command.patientId())
                .orElseThrow(() -> new AppointmentException("Patient not found"));
        hospitalRepository.findById(command.hospitalId())
                .orElseThrow(() -> new AppointmentException("Hospital not found"));

        Appointment appointment = Appointment.request(
                command.patientId(),
                command.hospitalId(),
                command.preferredDoctorId(),
                command.preferredTime(),
                command.patientNotes()
        );
        return toResult(appointmentRepository.save(appointment));
    }

    @Override
    public AppointmentResult approve(RespondToAppointmentCommand command) {
        return respond(command, (appointment, doctorId) ->
                appointment.approve(doctorId, command.scheduledAt(), command.notes()));
    }

    @Override
    public AppointmentResult reject(RespondToAppointmentCommand command) {
        return respond(command, (appointment, doctorId) ->
                appointment.reject(doctorId, command.notes()));
    }

    @Override
    public AppointmentResult reschedule(RespondToAppointmentCommand command) {
        return respond(command, (appointment, doctorId) ->
                appointment.reschedule(doctorId, command.scheduledAt(), command.notes()));
    }

    private AppointmentResult respond(RespondToAppointmentCommand command, AppointmentAction action) {
        Appointment appointment = appointmentRepository.findById(command.appointmentId())
                .orElseThrow(() -> new AppointmentException("Appointment not found"));
        Appointment updated = action.apply(appointment, command.doctorId());
        return toResult(appointmentRepository.save(updated));
    }

    private AppointmentResult toResult(Appointment appointment) {
        return new AppointmentResult(
                appointment.id(),
                appointment.patientId(),
                appointment.doctorId(),
                appointment.hospitalId(),
                appointment.requestedAt(),
                appointment.scheduledAt(),
                appointment.status(),
                appointment.patientNotes(),
                appointment.doctorResponseNotes()
        );
    }

    @FunctionalInterface
    private interface AppointmentAction {
        Appointment apply(Appointment appointment, java.util.UUID doctorId);
    }
}
