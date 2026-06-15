package com.tenalink.infrastructure.persistence.repository;

import com.tenalink.infrastructure.persistence.entity.HospitalJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HospitalJpaRepository extends JpaRepository<HospitalJpaEntity, UUID> {}
