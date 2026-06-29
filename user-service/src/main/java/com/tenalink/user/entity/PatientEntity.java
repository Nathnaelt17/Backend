package com.tenalink.user.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
@Entity @Table(name = "patients") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PatientEntity {
    @Id private UUID id;
    @Column(nullable = false, unique = true) private UUID userId;
    @Column(nullable = false) private String faydaId;
    @Column(nullable = false) private String fullName;
    @Column(nullable = false) private String dateOfBirth;
    @Column(nullable = false) private String gender;
    @Column(nullable = false) private String contactPhone;
}
