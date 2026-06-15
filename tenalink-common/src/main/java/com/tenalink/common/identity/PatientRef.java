package com.tenalink.common.identity;

import java.util.Objects;
import java.util.UUID;

/**
 * Opaque patient identity owned by user-service ({@code patients.user_id}).
 */
public record PatientRef(UUID value) {
    public PatientRef {
        Objects.requireNonNull(value, "patientId must not be null");
    }

    public UserRef asUserRef() {
        return new UserRef(value);
    }
}
