package com.tenalink.infrastructure.config;

import com.tenalink.application.service.*;
import com.tenalink.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    AuthApplicationService authApplicationService(UserRepository userRepository, PasswordHasher passwordHasher, TokenService tokenService) {
        return new AuthApplicationService(userRepository, passwordHasher, tokenService);
    }
}
