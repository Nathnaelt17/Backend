package com.tenalink.application.service;

import com.tenalink.application.dto.*;
import com.tenalink.application.usecase.*;
import com.tenalink.domain.entity.User;
import com.tenalink.domain.exception.AuthException;
import com.tenalink.domain.repository.UserRepository;

public class AuthApplicationService implements RegisterUserUseCase, LoginUseCase, RefreshTokenUseCase {
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final TokenService tokenService;

    public AuthApplicationService(UserRepository userRepository, PasswordHasher passwordHasher, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.tokenService = tokenService;
    }

    @Override
    public AuthResult register(RegisterCommand command) {
        if (userRepository.existsByEmail(command.email().toLowerCase())) {
            throw new AuthException("Email is already registered");
        }
        User user = userRepository.save(User.register(command.email(), passwordHasher.hash(command.password()), command.fullName(), command.role()));
        return result(user);
    }

    @Override
    public AuthResult login(LoginCommand command) {
        User user = userRepository.findByEmail(command.email().toLowerCase()).orElseThrow(() -> new AuthException("Invalid credentials"));
        if (!passwordHasher.matches(command.password(), user.passwordHash())) {
            throw new AuthException("Invalid credentials");
        }
        return result(user);
    }

    @Override
    public AuthResult refresh(RefreshCommand command) {
        if (!"refresh".equals(tokenService.type(command.refreshToken()))) {
            throw new AuthException("Invalid refresh token");
        }
        User user = userRepository.findById(tokenService.subject(command.refreshToken())).orElseThrow(() -> new AuthException("User no longer exists"));
        return result(user);
    }

    private AuthResult result(User user) {
        return new AuthResult(user.id(), user.email(), user.fullName(), user.role(), tokenService.accessToken(user), tokenService.refreshToken(user));
    }
}
