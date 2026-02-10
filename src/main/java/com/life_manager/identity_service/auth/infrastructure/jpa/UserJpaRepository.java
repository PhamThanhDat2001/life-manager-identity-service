package com.life_manager.identity_service.auth.infrastructure.jpa;

import com.life_manager.identity_service.auth.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);

    // check username tồn tại chưa
    boolean existsByUsername(String username);

    @Query("""
select distinct u from UserEntity u
left join fetch u.roles ur
left join fetch ur.role r
left join fetch r.rolePermissions rp
left join fetch rp.permission p
""")
    List<UserEntity> findAllWithRolesAndPermissions();
}