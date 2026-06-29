package com.tenalink.user.controller;

import com.tenalink.user.dto.PatientDto;
import com.tenalink.user.entity.PatientEntity;
import com.tenalink.user.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);
    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto.Response> get(@PathVariable UUID id) {
        PatientEntity patient;
        try {
            // Try to find by patient ID first
            patient = service.get(id);
        } catch (Exception e) {
            // If not found, try to find by userId (the frontend might pass userId instead of patientId)
            logger.info("Patient not found by id {}, trying userId lookup", id);
            patient = service.getByUserId(id);
        }
        return ResponseEntity.ok(toResponse(patient));
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<PatientDto.Response> getByUserId(@PathVariable UUID userId) {
        PatientEntity patient = service.getByUserId(userId);
        return ResponseEntity.ok(toResponse(patient));
    }

    @PostMapping("/{id}")
    public ResponseEntity<PatientDto.Response> upsert(@PathVariable UUID id, @RequestBody PatientDto.UpsertRequest req) {
        PatientEntity patient = service.upsert(id, req);
        return ResponseEntity.ok(toResponse(patient));
    }

    private PatientDto.Response toResponse(PatientEntity patient) {
        PatientDto.Response response = new PatientDto.Response();
        response.setId(patient.getId());
        response.setUserId(patient.getUserId());
        response.setFaydaId(patient.getFaydaId());
        response.setFullName(patient.getFullName());
        response.setFirstName(extractFirstName(patient.getFullName()));
        response.setLastName(extractLastName(patient.getFullName()));
        response.setDateOfBirth(patient.getDateOfBirth());
        response.setGender(patient.getGender());
        response.setContactPhone(patient.getContactPhone());
        response.setBloodType(patient.getBloodType());
        response.setAllergies(patient.getAllergies());
        response.setChronicConditions(patient.getChronicConditions());
        return response;
    }

    private String extractFirstName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            return "";
        }
        String[] parts = fullName.trim().split(" ", 2);
        return parts[0];
    }

    private String extractLastName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            return "";
        }
        String[] parts = fullName.trim().split(" ", 2);
        return parts.length > 1 ? parts[1] : "";
    }
}
