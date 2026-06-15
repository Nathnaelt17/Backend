package com.tenalink.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

public record GatewayPrincipal(UUID userId, Collection<? extends GrantedAuthority> authorities) {

    public String primaryRole() {
        return authorities.stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .map(role -> role.startsWith("ROLE_") ? role.substring(5) : role)
                .orElse("UNKNOWN");
    }
}
