package com.tenalink.common.identity;

import java.util.Objects;
import java.util.UUID;

/**
 * Opaque hospital identity owned by user-service.
 */
public record HospitalRef(UUID value) {
    public HospitalRef {
        Objects.requireNonNull(value, "hospitalId must not be null");
    }
}
