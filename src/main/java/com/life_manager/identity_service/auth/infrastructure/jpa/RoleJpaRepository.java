package com.life_manager.identity_service.auth.infrastructure.jpa;

import com.life_manager.identity_service.auth.domain.entity.RoleEntity;
import com.life_manager.identity_service.auth.domain.repo.RoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long>, RoleRepository {
    Optional<RoleEntity> findByRole(RoleEntity role);
    boolean existsByRole(RoleEntity role);

//    @Query(value = """
//            SELECT * FROM roles r
//            LEFT JOIN role_permissions rp ON r.id = rp.role_id
//            LEFT JOIN permissions p ON p.id = rp.permission_id
//            """, nativeQuery = true)
//     findAllRoleWithPermissions();

    @Query("""
    select distinct r from RoleEntity r
    left join fetch r.rolePermissions rp
    left join fetch rp.permission p
""")
    List<RoleEntity> findAllWithPermissions();

}
