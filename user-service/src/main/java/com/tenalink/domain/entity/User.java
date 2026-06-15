package com.tenalink.domain.entity;

import com.tenalink.domain.enumtype.Role;

import java.util.UUID;

public record User(UUID id, String email, String fullName, Role role) {
}
