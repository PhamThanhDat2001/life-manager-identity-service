package com.life_manager.identity_service.auth.application.service;

import com.life_manager.identity_service.auth.domain.entity.UserEntity;

public interface IRoleService {
    void assignDefaultRoles(UserEntity user);

//    void addPermissionToRole(Long roleId, Set<Long> permissionIds);
}
