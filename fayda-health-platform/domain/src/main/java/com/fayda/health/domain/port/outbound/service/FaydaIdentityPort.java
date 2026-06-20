package com.fayda.health.domain.port.outbound.service;

import com.fayda.health.domain.valueobject.FaydaId;

/**
 * Outbound port for Ethiopia Fayda National ID verification API.
 */
public interface FaydaIdentityPort {

    FaydaVerificationResult verify(FaydaId faydaId, String fullName);

    record FaydaVerificationResult(boolean verified, String legalName, String message) {}
}
