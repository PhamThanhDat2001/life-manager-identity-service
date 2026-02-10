package com.life_manager.identity_service.auth.infrastructure;

import com.life_manager.identity_service.auth.domain.entity.RolePermissionEntity;
import com.life_manager.identity_service.auth.infrastructure.jpa.RolePermissionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RolePermissionImpl implements com.life_manager.identity_service.auth.domain.repo.RolePermissionRepository {

    private final RolePermissionJpaRepository jpa;

    @Override
    public RolePermissionEntity save(RolePermissionEntity rolePermissionEntity) {
        return jpa.save(rolePermissionEntity);
    }

    @Override
    public List<RolePermissionEntity> saveAll(List<RolePermissionEntity> list) {
        return jpa.saveAll(list);
    }
}
