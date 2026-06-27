package com.tenalink.appointment.dto;
import lombok.Data;
import java.time.Instant;
import java.util.UUID;
public class AppointmentDto {
    @Data public static class CreateRequest { private UUID patientId; private UUID doctorId; private UUID hospitalId; private Instant scheduledAt; }
}
