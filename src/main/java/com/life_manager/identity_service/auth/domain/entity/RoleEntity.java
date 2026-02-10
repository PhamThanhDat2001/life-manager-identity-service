package com.life_manager.identity_service.auth.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Enumerated(EnumType.STRING)
    @Column(name = "name",nullable = false)
    private String role;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<UserRoleEntity> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<RolePermissionEntity> rolePermissions = new HashSet<>();

    private String description;
}
