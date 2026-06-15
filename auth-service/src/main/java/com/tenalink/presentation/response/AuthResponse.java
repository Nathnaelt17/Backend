package com.tenalink.presentation.response;

import com.tenalink.domain.enumtype.Role;

import java.util.UUID;

public record AuthResponse(UUID userId, String email, String fullName, Role role, String accessToken, String refreshToken) {
}
