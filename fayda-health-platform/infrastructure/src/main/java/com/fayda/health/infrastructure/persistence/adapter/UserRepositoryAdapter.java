package com.fayda.health.infrastructure.persistence.adapter;

import com.fayda.health.domain.model.User;
import com.fayda.health.domain.port.outbound.repository.UserRepository;
import com.fayda.health.domain.valueobject.Email;
import com.fayda.health.domain.valueobject.FaydaId;
import com.fayda.health.infrastructure.persistence.entity.UserJpaEntity;
import com.fayda.health.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        return toDomain(jpaRepository.save(toJpa(user)));
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return jpaRepository.findByEmail(email.value()).map(this::toDomain);
    }

    @Override
    public Optional<User> findByFaydaId(FaydaId faydaId) {
        return jpaRepository.findByFaydaId(faydaId.value()).map(this::toDomain);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return jpaRepository.existsByEmail(email.value());
    }

    @Override
    public boolean existsByFaydaId(FaydaId faydaId) {
        return jpaRepository.existsByFaydaId(faydaId.value());
    }

    private UserJpaEntity toJpa(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.id());
        entity.setEmail(user.email() != null ? user.email().value() : null);
        entity.setFaydaId(user.faydaId() != null ? user.faydaId().value() : null);
        entity.setPasswordHash(user.passwordHash());
        entity.setFullName(user.fullName());
        entity.setRole(user.role());
        entity.setCreatedAt(user.createdAt());
        return entity;
    }

    private User toDomain(UserJpaEntity entity) {
        return User.restore(
                entity.getId(),
                entity.getEmail() != null ? Email.of(entity.getEmail()) : null,
                entity.getFaydaId() != null ? FaydaId.of(entity.getFaydaId()) : null,
                entity.getPasswordHash(),
                entity.getFullName(),
                entity.getRole(),
                entity.getCreatedAt()
        );
    }
}
