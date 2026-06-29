package com.tenalink.user.service;

import com.tenalink.user.dto.PatientDto;
import com.tenalink.user.entity.PatientEntity;
import com.tenalink.user.exception.PatientNotFoundException;
import com.tenalink.user.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PatientService {
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository repo;

    public PatientService(PatientRepository repo) {
        this.repo = repo;
    }

    public PatientEntity upsert(UUID id, PatientDto.UpsertRequest req) {
        PatientEntity p = repo.findById(id).orElse(new PatientEntity());
        p.setId(id);
        p.setFaydaId(req.getFaydaId());
        p.setFullName(req.getFullName());
        p.setDateOfBirth(req.getDateOfBirth());
        p.setGender(req.getGender());
        p.setContactPhone(req.getContactPhone());
        return repo.save(p);
    }

    public PatientEntity get(UUID id) {
        logger.info("Resolving patient by id {}", id);
        return repo.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Patient not found for id {}", id);
                    return new PatientNotFoundException("Patient not found for id: " + id);
                });
    }

    public PatientEntity getByUserId(UUID userId) {
        logger.info("Resolving patient for userId {}", userId);
        return repo.findByUserId(userId)
                .orElseThrow(() -> {
                    logger.warn("Patient not found for userId {}", userId);
                    return new PatientNotFoundException("Patient not found for userId: " + userId);
                });
    }
}
