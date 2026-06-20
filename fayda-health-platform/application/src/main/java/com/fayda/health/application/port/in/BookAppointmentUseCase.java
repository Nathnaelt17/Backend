package com.fayda.health.application.port.in;

import com.fayda.health.application.dto.AppointmentResult;
import com.fayda.health.application.dto.BookAppointmentCommand;

public interface BookAppointmentUseCase {
    AppointmentResult book(BookAppointmentCommand command);
}
