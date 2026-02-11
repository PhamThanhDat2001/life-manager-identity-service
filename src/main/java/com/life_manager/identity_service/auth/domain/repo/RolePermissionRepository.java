package com.life_manager.identity_service.auth.domain.repo;

import com.life_manager.identity_service.auth.domain.entity.RolePermissionEntity;

import java.util.List;

public interface RolePermissionRepository {
    RolePermissionEntity save(RolePermissionEntity rolePermissionEntity);

    List<RolePermissionEntity> saveAll(List<RolePermissionEntity> list);
}
