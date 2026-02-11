package com.life_manager.identity_service.auth.infrastructure;

import com.life_manager.identity_service.auth.application.enums.Role;
import com.life_manager.identity_service.auth.domain.entity.RoleEntity;
import com.life_manager.identity_service.auth.domain.repo.RoleRepository;
import com.life_manager.identity_service.auth.infrastructure.jpa.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

   private final RoleJpaRepository roleJpaRepository;

    @Override
    public Optional<RoleEntity> findByRole(Role role) {
        return roleJpaRepository.findByRole(role);
    }

    @Override
    public Optional<RoleEntity> findById(Long roleId) {
        return roleJpaRepository.findById(roleId);
    }

    @Override
    public RoleEntity save(RoleEntity roleEntity) {
        return roleJpaRepository.save(roleEntity);
    }

    @Override
    public List<RoleEntity> findAllWithPermissions() {
        return roleJpaRepository.findAllWithPermissions();
    }

    @Override
    public Set<RoleEntity> findByRoleIn(Set<String> roles) {
        return roleJpaRepository.findByRoleIn(roles);
    }
}
