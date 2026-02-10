package com.life_manager.identity_service.auth.infrastructure;

import com.life_manager.identity_service.auth.domain.entity.RolePermissionEntity;
import com.life_manager.identity_service.auth.domain.entity.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionJpaRepository extends JpaRepository<RolePermissionEntity, RolePermissionId> {
}
