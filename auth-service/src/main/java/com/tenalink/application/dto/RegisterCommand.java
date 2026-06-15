package com.tenalink.application.dto;

import com.tenalink.domain.enumtype.Role;

public record RegisterCommand(String email, String password, String fullName, Role role) {
}
