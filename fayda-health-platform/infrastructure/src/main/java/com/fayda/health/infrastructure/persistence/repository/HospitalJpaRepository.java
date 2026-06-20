package com.fayda.health.infrastructure.persistence.repository;

import com.fayda.health.infrastructure.persistence.entity.HospitalJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HospitalJpaRepository extends JpaRepository<HospitalJpaEntity, UUID> {}
