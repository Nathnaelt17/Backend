package com.tenalink.user.controller;
import com.tenalink.user.dto.PatientDto;
import com.tenalink.user.entity.PatientEntity;
import com.tenalink.user.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
@RestController @RequestMapping("/api/v1/patients")
public class PatientController {
    private final PatientService service;
    public PatientController(PatientService service) { this.service = service; }
    @GetMapping("/{id}") public ResponseEntity<PatientEntity> get(@PathVariable UUID id) { return ResponseEntity.ok(service.get(id)); }
    @PostMapping("/{id}") public ResponseEntity<PatientEntity> upsert(@PathVariable UUID id, @RequestBody PatientDto.UpsertRequest req) { return ResponseEntity.ok(service.upsert(id, req)); }
}
