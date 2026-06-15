package com.tenalink.presentation.controller;

import com.tenalink.application.dto.IssuePrescriptionCommand;
import com.tenalink.application.usecase.PrescriptionUseCase;
import com.tenalink.domain.entity.Prescription;
import com.tenalink.presentation.request.PrescriptionRequest;
import com.tenalink.presentation.response.PrescriptionResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pharmacy")
public class PharmacyController {
    private final PrescriptionUseCase useCase;
    public PharmacyController(PrescriptionUseCase useCase) { this.useCase = useCase; }
    @PostMapping("/prescriptions") @ResponseStatus(HttpStatus.CREATED) PrescriptionResponse issue(@Valid @RequestBody PrescriptionRequest r) { return toResponse(useCase.issue(new IssuePrescriptionCommand(r.patientId(), r.doctorId(), r.hospitalId(), r.medication(), r.dosage()))); }
    @GetMapping("/patients/{patientId}/prescriptions") List<PrescriptionResponse> byPatient(@PathVariable UUID patientId) { return useCase.patientPrescriptions(patientId).stream().map(this::toResponse).toList(); }
    private PrescriptionResponse toResponse(Prescription p) { return new PrescriptionResponse(p.id(), p.patientId(), p.doctorId(), p.hospitalId(), p.medication(), p.dosage(), p.issuedAt(), p.status()); }
}
