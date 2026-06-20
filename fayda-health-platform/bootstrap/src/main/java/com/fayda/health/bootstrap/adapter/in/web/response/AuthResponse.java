package com.fayda.health.bootstrap.adapter.in.web.response;

import com.fayda.health.domain.enumtype.UserRole;

import java.util.UUID;

public record AuthResponse(
        UUID userId,
        String email,
        String faydaId,
        String fullName,
        UserRole role,
        String accessToken,
        String refreshToken
) {}
