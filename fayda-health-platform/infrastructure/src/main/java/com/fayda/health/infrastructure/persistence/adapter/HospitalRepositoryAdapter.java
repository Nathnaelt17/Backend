package com.fayda.health.infrastructure.persistence.adapter;

import com.fayda.health.domain.model.Hospital;
import com.fayda.health.domain.port.outbound.repository.HospitalRepository;
import com.fayda.health.infrastructure.persistence.entity.HospitalJpaEntity;
import com.fayda.health.infrastructure.persistence.repository.HospitalJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class HospitalRepositoryAdapter implements HospitalRepository {

    private final HospitalJpaRepository jpaRepository;

    public HospitalRepositoryAdapter(HospitalJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Hospital save(Hospital hospital) {
        return toDomain(jpaRepository.save(toJpa(hospital)));
    }

    @Override
    public Optional<Hospital> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Hospital> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    private HospitalJpaEntity toJpa(Hospital hospital) {
        HospitalJpaEntity entity = new HospitalJpaEntity();
        entity.setId(hospital.id());
        entity.setName(hospital.name());
        entity.setAddress(hospital.address());
        entity.setRegion(hospital.region());
        entity.setFaydaFacilityCode(hospital.faydaFacilityCode());
        return entity;
    }

    private Hospital toDomain(HospitalJpaEntity entity) {
        return Hospital.restore(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getRegion(),
                entity.getFaydaFacilityCode()
        );
    }
}
