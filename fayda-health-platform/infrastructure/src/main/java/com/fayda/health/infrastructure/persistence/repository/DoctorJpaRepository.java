// PATH: fayda-health-platform/infrastructure/src/main/java/com/fayda/health/infrastructure/persistence/repository/DoctorJpaRepository.java
package com.fayda.health.infrastructure.persistence.repository;

import com.fayda.health.infrastructure.persistence.entity.DoctorJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DoctorJpaRepository extends JpaRepository<DoctorJpaEntity, UUID> {
}

