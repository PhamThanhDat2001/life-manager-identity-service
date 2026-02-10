package com.life_manager.identity_service.auth.domain.repo;

import com.life_manager.identity_service.auth.application.enums.Role;
import com.life_manager.identity_service.auth.domain.entity.RoleEntity;


import java.util.Optional;

public interface RoleRepository {
    Optional<RoleEntity> findByRole(Role role);

    Optional<RoleEntity> findById(Long roleId);

    RoleEntity save(RoleEntity roleEntity);
}
