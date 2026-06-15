package com.tenalink.presentation.request;

import com.tenalink.domain.enumtype.Role;
import jakarta.validation.constraints.*;

public record RegisterRequest(@Email @NotBlank String email, @Size(min = 8) String password, @NotBlank String fullName, @NotNull Role role) {
}
