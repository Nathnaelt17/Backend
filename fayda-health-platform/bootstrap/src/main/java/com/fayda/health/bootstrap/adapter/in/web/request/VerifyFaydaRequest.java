package com.fayda.health.bootstrap.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record VerifyFaydaRequest(
        @NotBlank @Pattern(regexp = "\\d{12}") String faydaId,
        @NotBlank String fullName
) {}
