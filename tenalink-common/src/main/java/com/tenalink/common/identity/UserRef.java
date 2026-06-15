package com.tenalink.common.identity;

import java.util.Objects;
import java.util.UUID;

public record UserRef(UUID value) {
    public UserRef {
        Objects.requireNonNull(value, "userId must not be null");
    }
}
