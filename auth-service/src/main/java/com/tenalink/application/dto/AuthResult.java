package com.tenalink.application.dto;

import com.tenalink.domain.enumtype.Role;

import java.util.UUID;

public record AuthResult(UUID userId, String email, String fullName, Role role, String accessToken, String refreshToken) {
}
