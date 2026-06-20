package com.fayda.health.bootstrap.adapter.in.web;

import com.fayda.health.application.dto.VerifyFaydaCommand;
import com.fayda.health.application.port.in.VerifyFaydaIdUseCase;
import com.fayda.health.bootstrap.adapter.in.web.request.VerifyFaydaRequest;
import com.fayda.health.bootstrap.adapter.in.web.response.VerifyFaydaResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fayda")
public class FaydaController {

    private final VerifyFaydaIdUseCase verifyFaydaIdUseCase;

    public FaydaController(VerifyFaydaIdUseCase verifyFaydaIdUseCase) {
        this.verifyFaydaIdUseCase = verifyFaydaIdUseCase;
    }

    @PostMapping("/verify")
    public VerifyFaydaResponse verify(@Valid @RequestBody VerifyFaydaRequest request) {
        var result = verifyFaydaIdUseCase.verify(new VerifyFaydaCommand(request.faydaId(), request.fullName()));
        return new VerifyFaydaResponse(result.verified(), result.legalName(), result.message());
    }
}
