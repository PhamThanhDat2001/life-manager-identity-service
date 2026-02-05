package com.life_manager.identity_service.auth.infrastructure;

import com.life_manager.identity_service.auth.domain.RoleEntity;
import com.life_manager.identity_service.auth.domain.RoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long>, RoleRepository {

}
