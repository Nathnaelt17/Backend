package com.tenalink.infrastructure.persistence.adapter;

import com.tenalink.domain.entity.*;
import com.tenalink.domain.exception.UserDirectoryException;
import com.tenalink.domain.repository.UserDirectoryRepository;
import com.tenalink.infrastructure.persistence.entity.*;
import com.tenalink.infrastructure.persistence.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserDirectoryRepositoryAdapter implements UserDirectoryRepository {
    private final HospitalJpaRepository hospitals;
    private final UserJpaRepository users;
    private final DoctorJpaRepository doctors;
    private final PatientJpaRepository patients;

    public UserDirectoryRepositoryAdapter(HospitalJpaRepository hospitals, UserJpaRepository users, DoctorJpaRepository doctors, PatientJpaRepository patients) {
        this.hospitals = hospitals; this.users = users; this.doctors = doctors; this.patients = patients;
    }

    public Hospital saveHospital(Hospital hospital) { return toDomain(hospitals.save(toJpa(hospital))); }
    public List<Hospital> hospitals() { return hospitals.findAll().stream().map(this::toDomain).toList(); }
    public User saveUser(User user) { return toDomain(users.save(toJpa(user))); }
    public User user(UUID id) { return users.findById(id).map(this::toDomain).orElseThrow(() -> new UserDirectoryException("User not found")); }
    public Doctor saveDoctor(Doctor doctor) { return toDomain(doctors.save(toJpa(doctor))); }
    public Patient savePatient(Patient patient) { return toDomain(patients.save(toJpa(patient))); }

    private HospitalJpaEntity toJpa(Hospital h) { HospitalJpaEntity e = new HospitalJpaEntity(); e.setId(h.id()); e.setName(h.name()); e.setAddress(h.address()); return e; }
    private UserJpaEntity toJpa(User u) { UserJpaEntity e = new UserJpaEntity(); e.setId(u.id()); e.setEmail(u.email()); e.setFullName(u.fullName()); e.setRole(u.role()); return e; }
    private DoctorJpaEntity toJpa(Doctor d) { DoctorJpaEntity e = new DoctorJpaEntity(); e.setUserId(d.userId()); e.setHospitalId(d.hospitalId()); e.setSpecialty(d.specialty()); return e; }
    private PatientJpaEntity toJpa(Patient p) { PatientJpaEntity e = new PatientJpaEntity(); e.setUserId(p.userId()); e.setDateOfBirth(p.dateOfBirth()); e.setPhoneNumber(p.phoneNumber()); return e; }
    private Hospital toDomain(HospitalJpaEntity e) { return new Hospital(e.getId(), e.getName(), e.getAddress()); }
    private User toDomain(UserJpaEntity e) { return new User(e.getId(), e.getEmail(), e.getFullName(), e.getRole()); }
    private Doctor toDomain(DoctorJpaEntity e) { return new Doctor(e.getUserId(), e.getHospitalId(), e.getSpecialty()); }
    private Patient toDomain(PatientJpaEntity e) { return new Patient(e.getUserId(), e.getDateOfBirth(), e.getPhoneNumber()); }
}
