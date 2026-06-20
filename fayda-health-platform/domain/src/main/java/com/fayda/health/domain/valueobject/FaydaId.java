package com.fayda.health.domain.valueobject;

import com.fayda.health.domain.exception.DomainException;

import java.util.Objects;

/**
 * Ethiopia Fayda National ID — exactly 12 numeric digits.
 */
public record FaydaId(String value) {

    public FaydaId {
        Objects.requireNonNull(value, "Fayda ID is required");
        if (!value.matches("\\d{12}")) {
            throw new DomainException("Fayda ID must be exactly 12 digits");
        }
    }

    public static FaydaId of(String raw) {
        return new FaydaId(raw.trim());
    }

    @Override
    public String toString() {
        return value;
    }
}
