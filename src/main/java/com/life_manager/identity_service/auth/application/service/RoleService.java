package com.life_manager.identity_service.auth.application.service;

import com.life_manager.identity_service.auth.domain.UserEntity;

public interface RoleService {
    void assignDefaultRoles(UserEntity user);
}
