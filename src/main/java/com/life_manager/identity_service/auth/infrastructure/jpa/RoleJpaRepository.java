package com.life_manager.identity_service.auth.infrastructure.jpa;

import com.life_manager.identity_service.auth.application.enums.Role;
import com.life_manager.identity_service.auth.domain.entity.RoleEntity;
import com.life_manager.identity_service.auth.domain.repo.RoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {

    @Query("""
    select distinct r from RoleEntity r
    left join fetch r.rolePermissions rp
    left join fetch rp.permission p
""")
    List<RoleEntity> findAllWithPermissions();

    Optional<RoleEntity> findByRole(String role);

    Set<RoleEntity> findByRoleIn(Set<String> roles);
}
