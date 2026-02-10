package com.life_manager.identity_service.auth.application.mapper;

import com.life_manager.identity_service.auth.application.dto.request.CreatePermissionRequest;
import com.life_manager.identity_service.auth.application.dto.response.PermissionResponse;
import com.life_manager.identity_service.auth.domain.entity.PermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    @Mapping(target = "permission", source = "name")
    PermissionEntity toPermissionEntity(CreatePermissionRequest request);

    @Mapping(target = "name", source = "permission")
    PermissionResponse toPermissionResponse(PermissionEntity permissionEntity);
}
