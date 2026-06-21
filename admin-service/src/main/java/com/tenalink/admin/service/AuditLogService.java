package com.tenalink.admin.service;
import com.tenalink.admin.dto.AuditLogDto;
import com.tenalink.admin.entity.AuditLogEntity;
import com.tenalink.admin.repository.AuditLogRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
@Service
public class AuditLogService {
    private final AuditLogRepository repo;
    public AuditLogService(AuditLogRepository repo) { this.repo = repo; }
    public AuditLogEntity create(AuditLogDto.CreateRequest req) {
        AuditLogEntity a = new AuditLogEntity();
        a.setAdminId(req.getAdminId()); a.setAction(req.getAction()); a.setTargetResource(req.getTargetResource());
        return repo.save(a);
    }
    public List<AuditLogEntity> getByAdmin(UUID adminId) { return repo.findByAdminId(adminId); }
}
