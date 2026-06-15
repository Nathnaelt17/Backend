package com.tenalink.domain.entity;

import java.util.UUID;

public record Hospital(UUID id, String name, String address) {
    public static Hospital create(String name, String address) {
        return new Hospital(UUID.randomUUID(), name, address);
    }
}
