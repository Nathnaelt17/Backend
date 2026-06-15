package com.tenalink.common.identity;

import java.util.Objects;
import java.util.UUID;

/**
 * Opaque doctor identity owned by user-service ({@code doctors.user_id}).
 */
public record DoctorRef(UUID value) {
    public DoctorRef {
        Objects.requireNonNull(value, "doctorId must not be null");
    }

    public UserRef asUserRef() {
        return new UserRef(value);
    }
}
