package com.tenalink.application.usecase;

import com.tenalink.application.dto.UserCommands.*;
import com.tenalink.domain.entity.*;

import java.util.List;
import java.util.UUID;

public interface UserDirectoryUseCase {
    Hospital createHospital(CreateHospital command);
    List<Hospital> hospitals();
    User saveUser(SaveUser command);
    User user(UUID id);
    Doctor saveDoctor(SaveDoctor command);
    Patient savePatient(SavePatient command);
}
