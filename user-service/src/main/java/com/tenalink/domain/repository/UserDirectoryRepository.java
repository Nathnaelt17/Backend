package com.tenalink.domain.repository;

import com.tenalink.domain.entity.*;

import java.util.List;
import java.util.UUID;

public interface UserDirectoryRepository {
    Hospital saveHospital(Hospital hospital);
    List<Hospital> hospitals();
    User saveUser(User user);
    User user(UUID id);
    Doctor saveDoctor(Doctor doctor);
    Patient savePatient(Patient patient);
}
