package com.life_manager.identity_service.auth.application.service;

import com.life_manager.identity_service.auth.application.dto.request.CreatePermissionRequest;
import com.life_manager.identity_service.auth.application.dto.response.PermissionResponse;
import com.life_manager.identity_service.auth.application.mapper.PermissionMapper;
import com.life_manager.identity_service.auth.domain.entity.PermissionEntity;
import com.life_manager.identity_service.auth.infrastructure.jpa.PermissionJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PermissionService {
    PermissionMapper permissionMapper;
    PermissionJpaRepository permissionJpaRepository;

    public PermissionResponse createPermission(CreatePermissionRequest request) {
        PermissionEntity permissionEntity = permissionMapper.toPermissionEntity(request);
        PermissionEntity saved = permissionJpaRepository.save(permissionEntity);
        return permissionMapper.toPermissionResponse(saved);
    }

    public List<PermissionResponse> getAllPermission() {
        var permissions = permissionJpaRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }
}
