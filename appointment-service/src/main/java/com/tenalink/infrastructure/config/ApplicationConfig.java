package com.tenalink.infrastructure.config;

import com.tenalink.application.service.AppointmentApplicationService;
import com.tenalink.domain.repository.AppointmentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean AppointmentApplicationService appointmentApplicationService(AppointmentRepository repository) { return new AppointmentApplicationService(repository); }
}
