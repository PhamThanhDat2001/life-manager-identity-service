package com.life_manager.identity_service.auth.application.service;

import com.life_manager.identity_service.auth.application.dto.request.CreateRoleRequest;
import com.life_manager.identity_service.auth.application.dto.response.RoleResponse;
import com.life_manager.identity_service.auth.application.enums.Permission;
import com.life_manager.identity_service.auth.application.enums.Role;
import com.life_manager.identity_service.auth.application.mapper.RoleMapper;
import com.life_manager.identity_service.auth.domain.entity.*;
import com.life_manager.identity_service.auth.domain.repo.PermissionRepository;
import com.life_manager.identity_service.auth.domain.repo.RolePermissionRepository;
import com.life_manager.identity_service.auth.domain.repo.RoleRepository;
import com.life_manager.identity_service.auth.domain.repo.UserRoleRepository;
import com.life_manager.identity_service.auth.infrastructure.*;
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
public class RoleService implements IRoleService {
    UserRoleRepository userRoleRepository;
    RoleMapper roleMapper;
    PermissionJpaRepository permissionJpaRepository;
    RolePermissionRepository rolePermissionRepository;
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;

//    @Override
//    @Transactional
//    public void assignDefaultRoles(UserEntity user) {
//
//        RoleEntity roleUser = roleJpaRepository.findByRole(Role.USER)
//                .orElseThrow();
//
//        user.addDefaultRoles(roleUser);
//
//    }

    @Override
    @Transactional
    public void assignDefaultRoles(UserEntity user) {
        RoleEntity roleUser = roleRepository.findByRole(Role.USER)
                .orElseThrow();

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUser(user);
        userRoleEntity.setRole(roleUser);

//        if (user.getRoles() == null) {
//            user.setRoles(new HashSet<>());
//        }
//        user.getRoles().add(userRoleEntity);

        userRoleRepository.save(userRoleEntity);
    }

//    @Override
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
}
