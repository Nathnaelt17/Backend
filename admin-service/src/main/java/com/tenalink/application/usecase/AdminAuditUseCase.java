package com.tenalink.application.usecase;

import com.tenalink.application.dto.AdminAuditCommand;
import com.tenalink.domain.entity.AuditLog;

import java.util.List;

public interface AdminAuditUseCase {
    AuditLog record(AdminAuditCommand command);
    List<AuditLog> audits();
}
