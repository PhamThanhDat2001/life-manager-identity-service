package com.life_manager.identity_service.auth.infrastructure;

import com.life_manager.identity_service.auth.domain.entity.UserEntity;
import com.life_manager.identity_service.auth.domain.repo.UserRepository;
import com.life_manager.identity_service.auth.infrastructure.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userJpaRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findById(String id) {
        return userJpaRepository.findById(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public void flush() {
        userJpaRepository.flush();
    }

    @Override
    public List<UserEntity> findAllWithRolesAndPermissions() {
        return userJpaRepository.findAllWithRolesAndPermissions();
    }
}

