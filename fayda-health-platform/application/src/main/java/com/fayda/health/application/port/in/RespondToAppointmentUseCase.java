package com.fayda.health.application.port.in;

import com.fayda.health.application.dto.AppointmentResult;
import com.fayda.health.application.dto.RespondToAppointmentCommand;

public interface RespondToAppointmentUseCase {
    AppointmentResult approve(RespondToAppointmentCommand command);

    AppointmentResult reject(RespondToAppointmentCommand command);

    AppointmentResult reschedule(RespondToAppointmentCommand command);
}
