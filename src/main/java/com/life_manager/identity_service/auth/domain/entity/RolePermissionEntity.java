package com.life_manager.identity_service.auth.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role_permissions")
@Builder
public class RolePermissionEntity {

    @EmbeddedId
    private RolePermissionId rolePermissionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id",  nullable = false)
    @MapsId("roleId")
    private RoleEntity role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id",  nullable = false)
    @MapsId("permissionId")
    private PermissionEntity permission;
}
