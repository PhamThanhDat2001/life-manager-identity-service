package com.life_manager.identity_service.auth.application.mapper;

import com.life_manager.identity_service.auth.application.dto.request.CreateRoleRequest;
import com.life_manager.identity_service.auth.application.dto.response.RoleResponse;
import com.life_manager.identity_service.auth.application.enums.Role;
import com.life_manager.identity_service.auth.domain.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "userRoles", ignore = true)
    @Mapping(target = "rolePermissions", ignore = true)
    @Mapping(target = "role", source = "name", qualifiedByName = "stringToRole")
    RoleEntity toRoleEntity(CreateRoleRequest request);

    RoleResponse toRoleResponse(RoleEntity entity);

    @Named("stringToRole")
    default Role stringToRole(String name) {
        if (name == null) {
            return null;
        }
        try {
            return Role.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role name: " + name);
        }
    }
}
