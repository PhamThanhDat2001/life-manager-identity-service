package com.life_manager.identity_service.auth.application.service;

import com.life_manager.identity_service.auth.application.dto.request.CreateRoleRequest;
import com.life_manager.identity_service.auth.application.dto.response.PermissionResponse;
import com.life_manager.identity_service.auth.application.dto.response.RoleResponse;
import com.life_manager.identity_service.auth.application.enums.Permission;
import com.life_manager.identity_service.auth.application.enums.Role;
import com.life_manager.identity_service.auth.application.mapper.RoleMapper;
import com.life_manager.identity_service.auth.domain.entity.*;
import com.life_manager.identity_service.auth.domain.repo.PermissionRepository;
import com.life_manager.identity_service.auth.domain.repo.RolePermissionRepository;
import com.life_manager.identity_service.auth.domain.repo.RoleRepository;
import com.life_manager.identity_service.auth.domain.repo.UserRoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoleService {
    UserRoleRepository userRoleRepository;
    RoleMapper roleMapper;
    RolePermissionRepository rolePermissionRepository;
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;

    @Transactional
    public void assignDefaultRoles(UserEntity user) {
        RoleEntity roleUser = roleRepository.findByRole(Role.USER)
                .orElseThrow();

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUser(user);
        userRoleEntity.setRole(roleUser);


        userRoleRepository.save(userRoleEntity);
    }

    @Transactional
    public void addPermissionToRole(Long roleId, Set<Long> permissionIds) {

        RoleEntity role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        List<PermissionEntity> permissions = permissionRepository.findAllByIdIn(permissionIds);

        List<RolePermissionEntity> list = permissions.stream()
                .map(p -> RolePermissionEntity.builder()
                        .rolePermissionId(new RolePermissionId(roleId, p.getId()))
                        .role(role)
                        .permission(p)
                        .build()
                ).toList();

        rolePermissionRepository.saveAll(list);
    }

    public RoleResponse createRole(CreateRoleRequest request) {

        RoleEntity roleEntity = roleMapper.toRoleEntity(request);

        Set<Permission> enums = request.getPermissions().stream()
                .map(Permission::valueOf)
                .collect(Collectors.toSet());

        Set<PermissionEntity> permissions =  permissionRepository.findByPermissionIn(enums);

        RoleEntity saved = roleRepository.save(roleEntity);
        addPermissionToRole(roleEntity.getId(), permissions.stream().map(PermissionEntity::getId).collect(Collectors.toSet()));

        return roleMapper.toRoleResponse(saved);
    }

    public List<RoleResponse> getAllRoles() {
        List<RoleEntity> roles = roleRepository.findAllWithPermissions();

        return roles.stream().map(role -> {
            Set<PermissionResponse> permissions = role.getRolePermissions()
                    .stream()
                    .map(rp -> {
                        PermissionEntity p = rp.getPermission();
                        return new PermissionResponse(
                                p.getPermission().name(),
                                p.getDescription()
                        );
                    })
                    .collect(Collectors.toSet());

            return RoleResponse.builder()
                    .role(role.getRole())
                    .permissions(permissions)
                    .build();
        }).toList();
    }

}
