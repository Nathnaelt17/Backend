package com.tenalink.user.service;

import com.tenalink.user.dto.PatientDto;
import com.tenalink.user.entity.PatientEntity;
import com.tenalink.user.exception.PatientNotFoundException;
import com.tenalink.user.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    private static final String UPLOAD_DIR = "uploads/patients/";

    private final PatientRepository repo;

    public PatientService(PatientRepository repo) {
        this.repo = repo;
    }

    public PatientEntity upsert(UUID id, PatientDto.UpsertRequest req, MultipartFile file) {

        PatientEntity p = repo.findById(id).orElse(new PatientEntity());

        p.setId(id);
        p.setFaydaId(req.getFaydaId());
        p.setFullName(req.getFullName());
        p.setDateOfBirth(req.getDateOfBirth());
        p.setGender(req.getGender());
        p.setContactPhone(req.getContactPhone());
        p.setBloodType(req.getBloodType());
        p.setAllergies(req.getAllergies());
        p.setChronicConditions(req.getChronicConditions());

        // =========================
        // FILE HANDLING (NEW)
        // =========================
        if (file != null && !file.isEmpty()) {
            try {
                File dir = new File(UPLOAD_DIR);
                if (!dir.exists()) dir.mkdirs();

                String originalName = file.getOriginalFilename();
                String extension = "";

                if (originalName != null && originalName.contains(".")) {
                    extension = originalName.substring(originalName.lastIndexOf("."));
                }

                String storedFileName = UUID.randomUUID() + extension;
                Path filePath = Paths.get(UPLOAD_DIR, storedFileName);

                Files.write(filePath, file.getBytes());

                p.setIdDocumentUrl(filePath.toString());
                p.setIdDocumentName(originalName);
                p.setIdDocumentUploadedAt(LocalDateTime.now());

            } catch (IOException e) {
                logger.error("Failed to store ID document", e);
                throw new RuntimeException("File upload failed");
            }
        }

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