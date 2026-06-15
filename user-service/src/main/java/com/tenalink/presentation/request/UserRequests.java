package com.tenalink.presentation.request;

import com.tenalink.domain.enumtype.Role;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public final class UserRequests {
    private UserRequests() {}
    public record HospitalRequest(@NotBlank String name, @NotBlank String address) {}
    public record UserRequest(@NotNull UUID id, @Email @NotBlank String email, @NotBlank String fullName, @NotNull Role role) {}
    public record DoctorRequest(@NotNull UUID userId, @NotNull UUID hospitalId, @NotBlank String specialty) {}
    public record PatientRequest(@NotNull UUID userId, LocalDate dateOfBirth, String phoneNumber) {}
}
