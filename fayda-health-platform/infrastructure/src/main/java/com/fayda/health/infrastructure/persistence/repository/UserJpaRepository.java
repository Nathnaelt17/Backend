package com.fayda.health.infrastructure.persistence.repository;

import com.fayda.health.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    Optional<UserJpaEntity> findByEmail(String email);
    Optional<UserJpaEntity> findByFaydaId(String faydaId);
    boolean existsByEmail(String email);
    boolean existsByFaydaId(String faydaId);
}
