package com.life_manager.identity_service.auth.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionId {
    public Long roleId;
    public Long permissionId;
}
