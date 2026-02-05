package com.life_manager.identity_service.auth.domain;

import com.life_manager.identity_service.auth.application.enums.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<RoleEntity> findByRole(Role role);
}
