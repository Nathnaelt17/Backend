package com.tenalink.presentation.controller;

import com.tenalink.application.dto.*;
import com.tenalink.application.usecase.*;
import com.tenalink.presentation.request.*;
import com.tenalink.presentation.response.AuthResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase, LoginUseCase loginUseCase, RefreshTokenUseCase refreshTokenUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUseCase = loginUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return toResponse(registerUserUseCase.register(new RegisterCommand(request.email(), request.password(), request.fullName(), request.role())));
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return toResponse(loginUseCase.login(new LoginCommand(request.email(), request.password())));
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@Valid @RequestBody RefreshRequest request) {
        return toResponse(refreshTokenUseCase.refresh(new RefreshCommand(request.refreshToken())));
    }

    @GetMapping("/me")
    public String me() {
        return "Use the access token claims for the authenticated principal";
    }

    private AuthResponse toResponse(AuthResult result) {
        return new AuthResponse(result.userId(), result.email(), result.fullName(), result.role(), result.accessToken(), result.refreshToken());
    }
}
