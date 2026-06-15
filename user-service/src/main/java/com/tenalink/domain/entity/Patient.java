package com.tenalink.domain.entity;

import java.time.LocalDate;
import java.util.UUID;

public record Patient(UUID userId, LocalDate dateOfBirth, String phoneNumber) {
}
