package com.life_manager.identity_service.auth.domain.repo;

import com.life_manager.identity_service.auth.domain.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    UserEntity save(UserEntity user);

    Optional<UserEntity> findById(String id);

    List<UserEntity> findAll();
}
