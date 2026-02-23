package com.life_manager.identity_service.auth.domain.repo;

import com.life_manager.identity_service.auth.application.enums.Role;
import com.life_manager.identity_service.auth.domain.entity.RoleEntity;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository {
    Optional<RoleEntity> findByRole(String role);

    Optional<RoleEntity> findById(Long roleId);

    RoleEntity save(RoleEntity roleEntity);

    List<RoleEntity> findAllWithPermissions();

    Set<RoleEntity> findByRoleIn(Set<String> roles);
}
