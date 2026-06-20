package com.fayda.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.fayda.health")
@EntityScan(basePackages = "com.fayda.health.infrastructure.persistence.entity")
@EnableJpaRepositories(basePackages = "com.fayda.health.infrastructure.persistence.repository")
public class FaydaHealthPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaydaHealthPlatformApplication.class, args);
    }
}
