package com.tenalink.infrastructure.config;

import com.tenalink.application.service.AdminAuditService;
import com.tenalink.domain.repository.AdminAuditRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean AdminAuditService adminAuditService(AdminAuditRepository repository) { return new AdminAuditService(repository); }
}
