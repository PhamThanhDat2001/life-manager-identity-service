package com.life_manager.identity_service.auth.application.dto.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class RoleDto {
     Long id;
     String name;
    private Set<PermissionDto> permissions; // flatten
}
