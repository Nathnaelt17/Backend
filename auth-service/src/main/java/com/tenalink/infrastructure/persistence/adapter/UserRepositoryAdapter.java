package com.tenalink.infrastructure.persistence.adapter;

import com.tenalink.domain.entity.User;
import com.tenalink.domain.repository.UserRepository;
import com.tenalink.infrastructure.persistence.entity.UserJpaEntity;
import com.tenalink.infrastructure.persistence.repository.UserJpaRepository;
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
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(this::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    private UserJpaEntity toJpa(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.id());
        entity.setEmail(user.email());
        entity.setPasswordHash(user.passwordHash());
        entity.setFullName(user.fullName());
        entity.setRole(user.role());
        entity.setCreatedAt(user.createdAt());
        return entity;
    }

    private User toDomain(UserJpaEntity entity) {
        return User.restore(entity.getId(), entity.getEmail(), entity.getPasswordHash(), entity.getFullName(), entity.getRole(), entity.getCreatedAt());
    }
}
