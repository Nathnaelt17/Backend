// PATH: fayda-health-platform/infrastructure/src/main/java/com/fayda/health/infrastructure/persistence/entity/DoctorJpaEntity.java
package com.fayda.health.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "doctors")
public class DoctorJpaEntity {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;

    @Column(name = "hospital_id", nullable = false)
    private UUID hospitalId;

    @Column(nullable = false, length = 100)
    private String specialty;

    @Column(name = "license_number", nullable = false, unique = true, length = 50)
    private String licenseNumber;

    public DoctorJpaEntity() {}

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public UserJpaEntity getUser() { return user; }
    public void setUser(UserJpaEntity user) { this.user = user; }

    public UUID getHospitalId() { return hospitalId; }
    public void setHospitalId(UUID hospitalId) { this.hospitalId = hospitalId; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
}