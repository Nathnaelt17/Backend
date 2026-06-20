package com.fayda.health.infrastructure.config;

import com.fayda.health.application.port.in.*;
import com.fayda.health.application.service.*;
import com.fayda.health.domain.port.outbound.repository.*;
import com.fayda.health.domain.port.outbound.service.FaydaIdentityPort;
import com.fayda.health.domain.port.outbound.service.PasswordHasherPort;
import com.fayda.health.domain.port.outbound.service.TokenPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestClient;

@Configuration
public class InfrastructureConfig {

    // PATH: fayda-health-platform/infrastructure/src/main/java/com/fayda/health/infrastructure/config/InfrastructureConfig.java

// ... (keep your existing class annotations and imports intact)

@Bean
public LoginUseCase loginUseCase(UserRepository userRepository,
                                 PatientRepository patientRepository,
                                 DoctorRepository doctorRepository,
                                 PasswordHasherPort passwordHasherPort,
                                 TokenPort tokenPort) {
    return new AuthApplicationService(userRepository, patientRepository, doctorRepository, passwordHasherPort, tokenPort);
}

@Bean
public RegisterUserUseCase registerUserUseCase(UserRepository userRepository,
                                               PatientRepository patientRepository,
                                               DoctorRepository doctorRepository,
                                               PasswordHasherPort passwordHasherPort,
                                               TokenPort tokenPort) {
    return new AuthApplicationService(userRepository, patientRepository, doctorRepository, passwordHasherPort, tokenPort);
}
}