package com.tenalink.application.service;

import com.tenalink.application.dto.AdminAuditCommand;
import com.tenalink.application.usecase.AdminAuditUseCase;
import com.tenalink.domain.entity.AuditLog;
import com.tenalink.domain.repository.AdminAuditRepository;

import java.util.List;

public class AdminAuditService implements AdminAuditUseCase {
    private final AdminAuditRepository repository;
    public AdminAuditService(AdminAuditRepository repository) { this.repository = repository; }
    public AuditLog record(AdminAuditCommand command) { return repository.save(AuditLog.record(command.userId(), command.role(), command.hospitalId(), command.actionType(), command.targetRecordId())); }
    public List<AuditLog> audits() { return repository.findAll(); }
}
