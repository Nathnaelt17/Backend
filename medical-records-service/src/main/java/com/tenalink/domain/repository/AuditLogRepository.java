package com.tenalink.domain.repository;

import com.tenalink.domain.entity.AuditLog;

public interface AuditLogRepository {
    AuditLog save(AuditLog auditLog);
}
