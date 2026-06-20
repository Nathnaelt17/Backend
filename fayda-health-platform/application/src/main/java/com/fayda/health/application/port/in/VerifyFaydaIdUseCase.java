package com.fayda.health.application.port.in;

import com.fayda.health.application.dto.VerifyFaydaCommand;
import com.fayda.health.application.dto.VerifyFaydaResult;

public interface VerifyFaydaIdUseCase {
    VerifyFaydaResult verify(VerifyFaydaCommand command);
}
