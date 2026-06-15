package com.tenalink.infrastructure.config;

import com.tenalink.application.service.UserDirectoryService;
import com.tenalink.domain.repository.UserDirectoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean UserDirectoryService userDirectoryService(UserDirectoryRepository repository) { return new UserDirectoryService(repository); }
}
