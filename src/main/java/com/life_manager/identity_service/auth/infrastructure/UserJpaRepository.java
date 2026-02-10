package com.life_manager.identity_service.auth.infrastructure;

import com.life_manager.identity_service.auth.domain.entity.UserEntity;
import com.life_manager.identity_service.auth.domain.repo.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, String>, UserRepository {
}