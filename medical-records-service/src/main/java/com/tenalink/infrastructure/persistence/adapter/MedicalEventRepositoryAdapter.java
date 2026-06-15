package com.tenalink.infrastructure.persistence.adapter;

import com.tenalink.domain.entity.MedicalEvent;
import com.tenalink.domain.repository.MedicalEventRepository;
import com.tenalink.infrastructure.persistence.entity.MedicalEventJpaEntity;
import com.tenalink.infrastructure.persistence.repository.MedicalEventJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class MedicalEventRepositoryAdapter implements MedicalEventRepository {
    private final MedicalEventJpaRepository jpaRepository;

    public MedicalEventRepositoryAdapter(MedicalEventJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public MedicalEvent save(MedicalEvent event) {
        return toDomain(jpaRepository.save(toJpa(event)));
    }

    @Override
    public List<MedicalEvent> findTimelineByPatientId(UUID patientId) {
        return jpaRepository.findByPatientIdOrderByTimestampAsc(patientId).stream().map(this::toDomain).toList();
    }

    private MedicalEventJpaEntity toJpa(MedicalEvent event) {
        MedicalEventJpaEntity entity = new MedicalEventJpaEntity();
        entity.setId(event.id());
        entity.setPatientId(event.patientId());
        entity.setHospitalId(event.hospitalId());
        entity.setAuthorId(event.authorId());
        entity.setTimestamp(event.timestamp());
        entity.setEventType(event.eventType());
        entity.setEventData(event.eventData());
        return entity;
    }

    private MedicalEvent toDomain(MedicalEventJpaEntity entity) {
        return MedicalEvent.restore(entity.getId(), entity.getPatientId(), entity.getHospitalId(), entity.getAuthorId(), entity.getTimestamp(), entity.getEventType(), entity.getEventData());
    }
}
