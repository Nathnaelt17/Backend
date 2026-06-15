package com.tenalink.infrastructure.persistence.entity;

import com.tenalink.domain.enumtype.AdminActionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter @Setter @Entity @Table(name = "audit_logs")
public class AuditLogJpaEntity {
    @Id private UUID id;
    @Column(nullable = false) private UUID userId;
    @Column(nullable = false) private String role;
    @Column(nullable = false) private UUID hospitalId;
    @Column(nullable = false) private Instant timestamp;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private AdminActionType actionType;
    @Column(nullable = false) private UUID targetRecordId;
}
