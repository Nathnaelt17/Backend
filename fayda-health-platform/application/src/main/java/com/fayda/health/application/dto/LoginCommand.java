// PATH: fayda-health-platform/application/src/main/java/com/fayda/health/application/dto/LoginCommand.java
package com.fayda.health.application.dto;

/**
 * Commands represent the intended action from the user.
 * This encapsulates the identifier (Email or Fayda ID) and raw password.
 */
public record LoginCommand(
    String identifier,
    String password
) {}