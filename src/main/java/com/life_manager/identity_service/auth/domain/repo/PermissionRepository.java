package com.life_manager.identity_service.auth.domain.repo;

import com.life_manager.identity_service.auth.application.enums.Permission;
import com.life_manager.identity_service.auth.domain.entity.PermissionEntity;

import java.util.List;
import java.util.Set;

public interface PermissionRepository {
    List<PermissionEntity> findAllByIdIn(Set<Long> permissionIds);

    Set<PermissionEntity> findByPermissionIn(Set<Permission> permissions);
}
