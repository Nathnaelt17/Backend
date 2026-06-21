package com.tenalink.user.service;
import com.tenalink.user.dto.PatientDto;
import com.tenalink.user.entity.PatientEntity;
import com.tenalink.user.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;
@Service
public class PatientService {
    private final PatientRepository repo;
    public PatientService(PatientRepository repo) { this.repo = repo; }
    public PatientEntity upsert(UUID id, PatientDto.UpsertRequest req) {
        PatientEntity p = repo.findById(id).orElse(new PatientEntity());
        p.setId(id); p.setFaydaId(req.getFaydaId()); p.setFullName(req.getFullName());
        p.setDateOfBirth(req.getDateOfBirth()); p.setGender(req.getGender()); p.setContactPhone(req.getContactPhone());
        return repo.save(p);
    }
    public PatientEntity get(UUID id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Not found")); }
}
