// PATH: fayda-health-platform/bootstrap/src/main/java/com/fayda/health/bootstrap/adapter/in/web/request/LoginRequest.java
package com.fayda.health.bootstrap.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;

/**
 * Inbound web request payload for handling logins.
 * Uses 'identifier' to cleanly accept either an Email address or a Fayda ID string.
 */
public record LoginRequest(
    @NotBlank(message = "Login identifier cannot be empty.")
    String identifier,

    @NotBlank(message = "Password cannot be empty.")
    String password
) {}