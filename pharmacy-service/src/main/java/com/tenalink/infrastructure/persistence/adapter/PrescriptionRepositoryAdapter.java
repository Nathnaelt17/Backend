package com.tenalink.infrastructure.persistence.adapter;

import com.tenalink.domain.entity.Prescription;
import com.tenalink.domain.repository.PrescriptionRepository;
import com.tenalink.infrastructure.persistence.entity.PrescriptionJpaEntity;
import com.tenalink.infrastructure.persistence.repository.PrescriptionJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PrescriptionRepositoryAdapter implements PrescriptionRepository {
    private final PrescriptionJpaRepository jpaRepository;
    public PrescriptionRepositoryAdapter(PrescriptionJpaRepository jpaRepository) { this.jpaRepository = jpaRepository; }
    public Prescription save(Prescription prescription) { return toDomain(jpaRepository.save(toJpa(prescription))); }
    public List<Prescription> findByPatientId(UUID patientId) { return jpaRepository.findByPatientId(patientId).stream().map(this::toDomain).toList(); }
    private PrescriptionJpaEntity toJpa(Prescription p) { PrescriptionJpaEntity e = new PrescriptionJpaEntity(); e.setId(p.id()); e.setPatientId(p.patientId()); e.setDoctorId(p.doctorId()); e.setHospitalId(p.hospitalId()); e.setMedication(p.medication()); e.setDosage(p.dosage()); e.setIssuedAt(p.issuedAt()); e.setStatus(p.status()); return e; }
    private Prescription toDomain(PrescriptionJpaEntity e) { return new Prescription(e.getId(), e.getPatientId(), e.getDoctorId(), e.getHospitalId(), e.getMedication(), e.getDosage(), e.getIssuedAt(), e.getStatus()); }
}
