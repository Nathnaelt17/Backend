package com.fayda.health.domain.port.outbound.repository;

import com.fayda.health.domain.model.User;
import com.fayda.health.domain.valueobject.Email;
import com.fayda.health.domain.valueobject.FaydaId;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(UUID id);

    Optional<User> findByEmail(Email email);

    Optional<User> findByFaydaId(FaydaId faydaId);

    boolean existsByEmail(Email email);

    boolean existsByFaydaId(FaydaId faydaId);
}
