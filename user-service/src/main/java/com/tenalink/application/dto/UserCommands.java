package com.tenalink.application.dto;

import com.tenalink.domain.enumtype.Role;

import java.time.LocalDate;
import java.util.UUID;

public final class UserCommands {
    private UserCommands() {}
    public record CreateHospital(String name, String address) {}
    public record SaveUser(UUID id, String email, String fullName, Role role) {}
    public record SaveDoctor(UUID userId, UUID hospitalId, String specialty) {}
    public record SavePatient(UUID userId, LocalDate dateOfBirth, String phoneNumber) {}
}
