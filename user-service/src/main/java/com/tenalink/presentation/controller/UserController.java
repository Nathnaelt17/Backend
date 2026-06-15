package com.tenalink.presentation.controller;

import com.tenalink.application.dto.UserCommands.*;
import com.tenalink.application.usecase.UserDirectoryUseCase;
import com.tenalink.domain.entity.*;
import com.tenalink.presentation.request.UserRequests.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserDirectoryUseCase useCase;
    public UserController(UserDirectoryUseCase useCase) { this.useCase = useCase; }
    @PostMapping("/hospitals") @ResponseStatus(HttpStatus.CREATED) Hospital createHospital(@Valid @RequestBody HospitalRequest r) { return useCase.createHospital(new CreateHospital(r.name(), r.address())); }
    @GetMapping("/hospitals") List<Hospital> hospitals() { return useCase.hospitals(); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) User saveUser(@Valid @RequestBody UserRequest r) { return useCase.saveUser(new SaveUser(r.id(), r.email(), r.fullName(), r.role())); }
    @GetMapping("/{id}") User user(@PathVariable UUID id) { return useCase.user(id); }
    @PostMapping("/doctors") @ResponseStatus(HttpStatus.CREATED) Doctor saveDoctor(@Valid @RequestBody DoctorRequest r) { return useCase.saveDoctor(new SaveDoctor(r.userId(), r.hospitalId(), r.specialty())); }
    @PostMapping("/patients") @ResponseStatus(HttpStatus.CREATED) Patient savePatient(@Valid @RequestBody PatientRequest r) { return useCase.savePatient(new SavePatient(r.userId(), r.dateOfBirth(), r.phoneNumber())); }
}
