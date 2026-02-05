package com.life_manager.identity_service.auth.application.service;

import com.life_manager.identity_service.auth.application.enums.Role;
import com.life_manager.identity_service.auth.domain.RoleEntity;
import com.life_manager.identity_service.auth.domain.UserEntity;
import com.life_manager.identity_service.auth.domain.UserRoleEntity;
import com.life_manager.identity_service.auth.domain.UserRoleRepository;
import com.life_manager.identity_service.auth.infrastructure.*;
import com.life_manager.identity_service.core.exeption.AppException;
import com.life_manager.identity_service.core.exeption.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    UserRoleRepository userRoleRepository;
    RoleJpaRepository roleJpaRepository;

//    @Override
//    @Transactional
//    public void assignDefaultRoles(UserEntity user) {
//
//        RoleEntity roleUser = roleJpaRepository.findByRole(Role.USER)
//                .orElseThrow();
//
//        user.addDefaultRoles(roleUser);
//
//    }

    @Override
    @Transactional
    public void assignDefaultRoles(UserEntity user) {
        RoleEntity roleUser = roleJpaRepository.findByRole(Role.USER)
                .orElseThrow();

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUser(user);
        userRoleEntity.setRole(roleUser);

//        if (user.getRoles() == null) {
//            user.setRoles(new HashSet<>());
//        }
//        user.getRoles().add(userRoleEntity);

        userRoleRepository.save(userRoleEntity);
    }
}
