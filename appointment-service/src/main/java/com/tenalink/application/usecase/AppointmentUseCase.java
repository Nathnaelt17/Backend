package com.tenalink.application.usecase;

import com.tenalink.application.dto.AppointmentCommand;
import com.tenalink.domain.entity.Appointment;

import java.util.List;
import java.util.UUID;

public interface AppointmentUseCase {
    Appointment schedule(AppointmentCommand command);
    List<Appointment> patientAppointments(UUID patientId);
}
