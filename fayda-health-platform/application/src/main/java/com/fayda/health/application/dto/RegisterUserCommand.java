package com.fayda.health.application.dto;

import com.fayda.health.domain.enumtype.UserRole;

public record RegisterUserCommand(
        String email,
        String faydaId,
        String password,
        String fullName,
        UserRole role
) {}
