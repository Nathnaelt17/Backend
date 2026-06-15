package com.tenalink.application.service;

import com.tenalink.application.dto.AppointmentCommand;
import com.tenalink.application.usecase.AppointmentUseCase;
import com.tenalink.common.identity.DoctorRef;
import com.tenalink.common.identity.HospitalRef;
import com.tenalink.common.identity.PatientRef;
import com.tenalink.domain.entity.Appointment;
import com.tenalink.domain.repository.AppointmentRepository;

import java.util.List;
import java.util.UUID;

public class AppointmentApplicationService implements AppointmentUseCase {
    private final AppointmentRepository repository;

    public AppointmentApplicationService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public Appointment schedule(AppointmentCommand command) {
        return repository.save(Appointment.schedule(
                new PatientRef(command.patientId()),
                new DoctorRef(command.doctorId()),
                new HospitalRef(command.hospitalId()),
                command.scheduledAt()
        ));
    }

    public List<Appointment> patientAppointments(UUID patientId) {
        return repository.findByPatientId(patientId);
    }
}
