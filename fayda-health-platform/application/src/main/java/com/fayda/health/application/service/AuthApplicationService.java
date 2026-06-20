// PATH: fayda-health-platform/application/src/main/java/com/fayda/health/application/service/AuthApplicationService.java
package com.fayda.health.application.service;

import com.fayda.health.application.port.in.LoginUseCase;
import com.fayda.health.application.port.in.RegisterUserUseCase;
import com.fayda.health.application.dto.LoginCommand;
import com.fayda.health.application.dto.RegisterUserCommand;
import com.fayda.health.application.dto.AuthResult;
import com.fayda.health.domain.model.User;
import com.fayda.health.domain.enumtype.UserRole;
import com.fayda.health.domain.valueobject.Email;
import com.fayda.health.domain.valueobject.FaydaId;
import com.fayda.health.domain.exception.AuthException;
import com.fayda.health.domain.port.outbound.repository.UserRepository;
import com.fayda.health.domain.port.outbound.repository.PatientRepository;
import com.fayda.health.domain.port.outbound.repository.DoctorRepository;
import com.fayda.health.domain.port.outbound.service.PasswordHasherPort;
import com.fayda.health.domain.port.outbound.service.TokenPort;

public class AuthApplicationService implements LoginUseCase, RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordHasherPort passwordHasherPort;
    private final TokenPort tokenPort;

    public AuthApplicationService(UserRepository userRepository,
                                  PatientRepository patientRepository,
                                  DoctorRepository doctorRepository,
                                  PasswordHasherPort passwordHasherPort,
                                  TokenPort tokenPort) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.passwordHasherPort = passwordHasherPort;
        this.tokenPort = tokenPort;
    }

    @Override
    public AuthResult login(LoginCommand command) {
        Email searchEmail = (command.identifier() != null && command.identifier().contains("@")) ? new Email(command.identifier()) : null;
        FaydaId searchFaydaId = (searchEmail == null && command.identifier() != null) ? new FaydaId(command.identifier()) : null;

        User user = null;
        if (searchEmail != null) {
            user = userRepository.findByEmail(searchEmail).orElse(null);
        }
        if (user == null && searchFaydaId != null) {
            user = userRepository.findByFaydaId(searchFaydaId).orElse(null);
        }

        if (user == null) {
            throw new AuthException("Invalid credentials provided.");
        }

        if (!passwordHasherPort.matches(command.password(), user.passwordHash())) {
            throw new AuthException("Invalid credentials provided.");
        }

        // Generate tokens using the full User object
        String accessToken = tokenPort.accessToken(user);
        String refreshToken = tokenPort.refreshToken(user);

        String emailStr = user.email() != null ? user.email().value() : null;
        String faydaIdStr = user.faydaId() != null ? user.faydaId().value() : null;
        
        return new AuthResult(
                user.id(),
                emailStr,
                faydaIdStr,
                user.fullName(),
                user.role(),
                accessToken,
                refreshToken
        );
    }

    @Override
    public AuthResult register(RegisterUserCommand command) {
        Email domainEmail = (command.email() != null && !command.email().isBlank()) ? new Email(command.email()) : null;
        FaydaId domainFaydaId = (command.faydaId() != null && !command.faydaId().isBlank()) ? new FaydaId(command.faydaId()) : null;

        if (domainEmail != null && userRepository.findByEmail(domainEmail).isPresent()) {
            throw new AuthException("An account with this email address already exists.");
        }
        if (domainFaydaId != null && userRepository.findByFaydaId(domainFaydaId).isPresent()) {
            throw new AuthException("An account with this Fayda ID already exists.");
        }

        String securedHash = passwordHasherPort.hash(command.password());

        // Create user using the domain static factory registration method
        User newUser = User.register(
                domainEmail,
                domainFaydaId,
                securedHash,
                command.fullName(),
                command.role()
        );

        User savedUser = userRepository.save(newUser);

        // Provision specific profile systems bound directly to the user UUID
        if (savedUser.role() == UserRole.PATIENT) {
            patientRepository.initializeProfile(savedUser.id());
        } else if (savedUser.role() == UserRole.DOCTOR) {
            doctorRepository.initializeProfile(savedUser.id());
        }

        String accessToken = tokenPort.accessToken(savedUser);
        String refreshToken = tokenPort.refreshToken(savedUser);

        String emailStr = savedUser.email() != null ? savedUser.email().value() : null;
        String faydaIdStr = savedUser.faydaId() != null ? savedUser.faydaId().value() : null;

        return new AuthResult(
                savedUser.id(),
                emailStr,
                faydaIdStr,
                savedUser.fullName(),
                savedUser.role(),
                accessToken,
                refreshToken
        );
    }
}