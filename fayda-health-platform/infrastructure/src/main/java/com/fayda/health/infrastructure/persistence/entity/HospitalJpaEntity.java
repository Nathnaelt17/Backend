package com.fayda.health.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "hospitals")
public class HospitalJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(length = 100)
    private String region;

    @Column(name = "fayda_facility_code", length = 32)
    private String faydaFacilityCode;
}
