package com.tenalink.infrastructure.persistence.repository;

import com.tenalink.infrastructure.persistence.entity.DoctorJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorJpaRepository extends JpaRepository<DoctorJpaEntity, UUID> {}
