package com.tenalink.appointment.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;
@Entity @Table(name = "appointments") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AppointmentEntity {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    @Column(nullable = false) private UUID patientId;
    @Column(nullable = false) private UUID doctorId;
    @Column(nullable = false) private UUID hospitalId;
    @Column(nullable = false) private Instant scheduledAt;
    @Column(nullable = false) private String status;
}
