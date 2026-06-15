package com.tenalink.infrastructure.config;

import com.tenalink.application.mapper.MedicalEventMapper;
import com.tenalink.application.service.MedicalTimelineService;
import com.tenalink.domain.repository.AuditLogRepository;
import com.tenalink.domain.repository.MedicalEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    MedicalEventMapper medicalEventMapper() {
        return new MedicalEventMapper();
    }

    @Bean
    MedicalTimelineService medicalTimelineService(MedicalEventRepository eventRepository, AuditLogRepository auditLogRepository, MedicalEventMapper mapper) {
        return new MedicalTimelineService(eventRepository, auditLogRepository, mapper);
    }
}
