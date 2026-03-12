package com.life_manager.identity_service.auth.infrastructure.jpa;

import com.life_manager.identity_service.auth.domain.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
