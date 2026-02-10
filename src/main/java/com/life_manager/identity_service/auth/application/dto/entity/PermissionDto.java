package com.life_manager.identity_service.auth.application.dto.entity;

import com.life_manager.identity_service.auth.application.enums.Permission;
import lombok.Data;

@Data
public class PermissionDto {
    private int id;
    private Permission permission;
}
