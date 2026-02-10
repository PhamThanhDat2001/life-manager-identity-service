package com.life_manager.identity_service.auth.infrastructure;

import com.life_manager.identity_service.auth.domain.entity.UserRoleEntity;
import com.life_manager.identity_service.auth.domain.repo.UserRoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleJpaRepository extends JpaRepository<UserRoleEntity,Long>, UserRoleRepository {
}
