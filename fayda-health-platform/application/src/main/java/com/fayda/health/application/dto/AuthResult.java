package com.fayda.health.application.dto;

import com.fayda.health.domain.enumtype.UserRole;

import java.util.UUID;

public record AuthResult(
        UUID userId,
        String email,
        String faydaId,
        String fullName,
        UserRole role,
        String accessToken,
        String refreshToken
) {}
