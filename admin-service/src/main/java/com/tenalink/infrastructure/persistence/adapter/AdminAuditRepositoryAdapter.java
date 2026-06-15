package com.tenalink.infrastructure.persistence.adapter;

import com.tenalink.domain.entity.AuditLog;
import com.tenalink.domain.repository.AdminAuditRepository;
import com.tenalink.infrastructure.persistence.entity.AuditLogJpaEntity;
import com.tenalink.infrastructure.persistence.repository.AuditLogJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminAuditRepositoryAdapter implements AdminAuditRepository {
    private final AuditLogJpaRepository jpaRepository;
    public AdminAuditRepositoryAdapter(AuditLogJpaRepository jpaRepository) { this.jpaRepository = jpaRepository; }
    public AuditLog save(AuditLog auditLog) { return toDomain(jpaRepository.save(toJpa(auditLog))); }
    public List<AuditLog> findAll() { return jpaRepository.findAll().stream().map(this::toDomain).toList(); }
    private AuditLogJpaEntity toJpa(AuditLog a) { AuditLogJpaEntity e = new AuditLogJpaEntity(); e.setId(a.id()); e.setUserId(a.userId()); e.setRole(a.role()); e.setHospitalId(a.hospitalId()); e.setTimestamp(a.timestamp()); e.setActionType(a.actionType()); e.setTargetRecordId(a.targetRecordId()); return e; }
    private AuditLog toDomain(AuditLogJpaEntity e) { return new AuditLog(e.getId(), e.getUserId(), e.getRole(), e.getHospitalId(), e.getTimestamp(), e.getActionType(), e.getTargetRecordId()); }
}
