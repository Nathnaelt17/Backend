package com.fayda.health.application.service;

import com.fayda.health.application.dto.VerifyFaydaCommand;
import com.fayda.health.application.dto.VerifyFaydaResult;
import com.fayda.health.application.port.in.VerifyFaydaIdUseCase;
import com.fayda.health.domain.exception.FaydaVerificationException;
import com.fayda.health.domain.port.outbound.service.FaydaIdentityPort;
import com.fayda.health.domain.valueobject.FaydaId;

public class FaydaVerificationApplicationService implements VerifyFaydaIdUseCase {

    private final FaydaIdentityPort faydaIdentityPort;

    public FaydaVerificationApplicationService(FaydaIdentityPort faydaIdentityPort) {
        this.faydaIdentityPort = faydaIdentityPort;
    }

    @Override
    public VerifyFaydaResult verify(VerifyFaydaCommand command) {
        FaydaIdentityPort.FaydaVerificationResult result =
                faydaIdentityPort.verify(FaydaId.of(command.faydaId()), command.fullName());

        if (!result.verified()) {
            throw new FaydaVerificationException(result.message());
        }
        return new VerifyFaydaResult(true, result.legalName(), result.message());
    }
}
