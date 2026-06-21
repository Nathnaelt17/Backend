package com.tenalink.admin.controller;
import com.tenalink.admin.dto.AuditLogDto;
import com.tenalink.admin.entity.AuditLogEntity;
import com.tenalink.admin.service.AuditLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController @RequestMapping("/api/v1/audit-logs")
public class AuditLogController {
    private final AuditLogService service;
    public AuditLogController(AuditLogService service) { this.service = service; }
    @PostMapping public ResponseEntity<AuditLogEntity> create(@RequestBody AuditLogDto.CreateRequest req) { return ResponseEntity.ok(service.create(req)); }
    @GetMapping("/admin/{adminId}") public ResponseEntity<List<AuditLogEntity>> getByAdmin(@PathVariable UUID adminId) { return ResponseEntity.ok(service.getByAdmin(adminId)); }
}
