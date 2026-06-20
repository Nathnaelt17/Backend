package com.fayda.health.domain.port.outbound.repository;

import com.fayda.health.domain.model.Hospital;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HospitalRepository {

    Hospital save(Hospital hospital);

    Optional<Hospital> findById(UUID id);

    List<Hospital> findAll();
}
