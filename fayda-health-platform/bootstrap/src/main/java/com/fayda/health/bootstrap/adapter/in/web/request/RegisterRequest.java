// PATH: fayda-health-platform/bootstrap/src/main/java/com/fayda/health/bootstrap/adapter/in/web/request/RegisterRequest.java
package com.fayda.health.bootstrap.adapter.in.web.request;

import com.fayda.health.domain.enumtype.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    String email,
    String faydaId,
    
    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    String password,
                
    @NotBlank(message = "Full name cannot be blank.")
    String fullName,
    
    @NotNull(message = "User role is required.")
    UserRole role
) {}