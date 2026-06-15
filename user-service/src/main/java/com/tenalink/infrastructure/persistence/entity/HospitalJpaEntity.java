package com.tenalink.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter @Entity @Table(name = "hospitals")
public class HospitalJpaEntity {
    @Id private UUID id;
    @Column(nullable = false) private String name;
    @Column(nullable = false) private String address;
}
