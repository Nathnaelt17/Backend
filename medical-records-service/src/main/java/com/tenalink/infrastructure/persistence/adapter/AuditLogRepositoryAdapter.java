package com.tenalink.infrastructure.persistence.adapter;

import com.tenalink.domain.entity.AuditLog;
import com.tenalink.domain.repository.AuditLogRepository;
import com.tenalink.infrastructure.persistence.entity.AuditLogJpaEntity;
import com.tenalink.infrastructure.persistence.repository.AuditLogJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AuditLogRepositoryAdapter implements AuditLogRepository {
    private final AuditLogJpaRepository jpaRepository;

    public AuditLogRepositoryAdapter(AuditLogJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public AuditLog save(AuditLog auditLog) {
        return toDomain(jpaRepository.save(toJpa(auditLog)));
    }

    private AuditLogJpaEntity toJpa(AuditLog auditLog) {
        AuditLogJpaEntity entity = new AuditLogJpaEntity();
        entity.setId(auditLog.id());
        entity.setUserId(auditLog.userId());
        entity.setRole(auditLog.role());
        entity.setHospitalId(auditLog.hospitalId());
        entity.setTimestamp(auditLog.timestamp());
        entity.setActionType(auditLog.actionType());
        entity.setTargetRecordId(auditLog.targetRecordId());
        return entity;
    }

    private AuditLog toDomain(AuditLogJpaEntity entity) {
        return AuditLog.restore(entity.getId(), entity.getUserId(), entity.getRole(), entity.getHospitalId(), entity.getTimestamp(), entity.getActionType(), entity.getTargetRecordId());
    }
}
