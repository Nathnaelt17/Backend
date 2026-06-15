package com.tenalink.presentation.controller;

import com.tenalink.application.dto.AdminAuditCommand;
import com.tenalink.application.usecase.AdminAuditUseCase;
import com.tenalink.domain.entity.AuditLog;
import com.tenalink.presentation.request.AdminAuditRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminAuditUseCase useCase;
    public AdminController(AdminAuditUseCase useCase) { this.useCase = useCase; }
    @PostMapping("/audit-logs") @ResponseStatus(HttpStatus.CREATED) AuditLog record(@Valid @RequestBody AdminAuditRequest r) { return useCase.record(new AdminAuditCommand(r.userId(), r.role(), r.hospitalId(), r.actionType(), r.targetRecordId())); }
    @GetMapping("/audit-logs") List<AuditLog> audits() { return useCase.audits(); }
}
