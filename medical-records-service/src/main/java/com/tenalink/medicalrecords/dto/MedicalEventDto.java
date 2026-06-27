package com.tenalink.medicalrecords.dto;
import lombok.Data;
import java.time.Instant;
import java.util.UUID;
public class MedicalEventDto {
    @Data public static class CreateRequest { private UUID patientId; private UUID hospitalId; private UUID authorId; private Instant timestamp; private String eventType; private String eventData; }
}
