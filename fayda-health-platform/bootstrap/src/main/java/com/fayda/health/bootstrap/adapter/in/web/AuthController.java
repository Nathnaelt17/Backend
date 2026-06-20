// PATH: fayda-health-platform/bootstrap/src/main/java/com/fayda/health/bootstrap/adapter/in/web/AuthController.java
package com.fayda.health.bootstrap.adapter.in.web;

import com.fayda.health.application.port.in.LoginUseCase;
import com.fayda.health.application.port.in.RegisterUserUseCase;
import com.fayda.health.application.dto.LoginCommand;
import com.fayda.health.application.dto.RegisterUserCommand;
import com.fayda.health.application.dto.AuthResult;
import com.fayda.health.bootstrap.adapter.in.web.request.LoginRequest;
import com.fayda.health.bootstrap.adapter.in.web.request.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final RegisterUserUseCase registerUserUseCase;

    public AuthController(LoginUseCase loginUseCase, RegisterUserUseCase registerUserUseCase) {
        this.loginUseCase = loginUseCase;
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResult> login(@Valid @RequestBody LoginRequest request) {
        LoginCommand command = new LoginCommand(request.identifier(), request.password());
        AuthResult result = loginUseCase.login(command);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResult> register(@Valid @RequestBody RegisterRequest request) {
        RegisterUserCommand command = new RegisterUserCommand(
                request.email(),
                request.faydaId(),
                request.password(),
                request.fullName(),
                request.role()
        );
        AuthResult result = registerUserUseCase.register(command);
        return ResponseEntity.ok(result);
    }
}