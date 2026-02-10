package com.life_manager.identity_service.auth.infrastructure.jpa;

import com.life_manager.identity_service.auth.domain.entity.RoleEntity;
import com.life_manager.identity_service.auth.domain.repo.RoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long>, RoleRepository {
    Optional<RoleEntity> findByRole(RoleEntity role);
    boolean existsByRole(RoleEntity role);
}
