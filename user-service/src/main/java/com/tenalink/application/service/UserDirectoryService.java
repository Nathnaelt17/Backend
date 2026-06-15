package com.tenalink.application.service;

import com.tenalink.application.dto.UserCommands.*;
import com.tenalink.application.usecase.UserDirectoryUseCase;
import com.tenalink.domain.entity.*;
import com.tenalink.domain.repository.UserDirectoryRepository;

import java.util.List;
import java.util.UUID;

public class UserDirectoryService implements UserDirectoryUseCase {
    private final UserDirectoryRepository repository;

    public UserDirectoryService(UserDirectoryRepository repository) {
        this.repository = repository;
    }

    public Hospital createHospital(CreateHospital command) { return repository.saveHospital(Hospital.create(command.name(), command.address())); }
    public List<Hospital> hospitals() { return repository.hospitals(); }
    public User saveUser(SaveUser command) { return repository.saveUser(new User(command.id(), command.email(), command.fullName(), command.role())); }
    public User user(UUID id) { return repository.user(id); }
    public Doctor saveDoctor(SaveDoctor command) { return repository.saveDoctor(new Doctor(command.userId(), command.hospitalId(), command.specialty())); }
    public Patient savePatient(SavePatient command) { return repository.savePatient(new Patient(command.userId(), command.dateOfBirth(), command.phoneNumber())); }
}
