package com.life_manager.identity_service.auth.domain;

import com.life_manager.identity_service.auth.application.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name",nullable = false)
    private Role role;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<UserRoleEntity> users = new HashSet<>();
}
