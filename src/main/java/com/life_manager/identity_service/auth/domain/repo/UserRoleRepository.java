package com.life_manager.identity_service.auth.domain.repo;

import com.life_manager.identity_service.auth.domain.entity.UserRoleEntity;

public interface UserRoleRepository {
    UserRoleEntity save(UserRoleEntity userRoleEntity);
}
