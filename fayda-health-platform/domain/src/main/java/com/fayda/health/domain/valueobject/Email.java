package com.fayda.health.domain.valueobject;

import com.fayda.health.domain.exception.DomainException;

import java.util.Objects;

public record Email(String value) {

    public Email {
        Objects.requireNonNull(value, "Email is required");
        String normalized = value.trim().toLowerCase();
        if (!normalized.matches("^[\\w.+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new DomainException("Invalid email format");
        }
        value = normalized;
    }

    public static Email of(String raw) {
        return new Email(raw);
    }

    @Override
    public String toString() {
        return value;
    }
}
