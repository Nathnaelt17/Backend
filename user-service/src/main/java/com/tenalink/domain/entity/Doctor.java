package com.tenalink.domain.entity;

import java.util.UUID;

public record Doctor(UUID userId, UUID hospitalId, String specialty) {
}
