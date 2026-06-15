package com.tenalink.domain.repository;

import com.tenalink.domain.entity.AuditLog;

import java.util.List;

public interface AdminAuditRepository {
    AuditLog save(AuditLog auditLog);
    List<AuditLog> findAll();
}
