package com.life_manager.identity_service.auth.infrastructure.jpa;

import com.life_manager.identity_service.auth.domain.entity.PermissionEntity;
import com.life_manager.identity_service.auth.domain.repo.PermissionRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionJpaRepository extends JpaRepository<PermissionEntity, Long>, PermissionRepository {
}
