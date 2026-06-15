package com.tenalink.infrastructure.config;

import com.tenalink.application.service.PrescriptionApplicationService;
import com.tenalink.domain.repository.PrescriptionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean PrescriptionApplicationService prescriptionApplicationService(PrescriptionRepository repository) { return new PrescriptionApplicationService(repository); }
}
