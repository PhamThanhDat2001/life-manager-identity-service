package com.life_manager.identity_service.auth.application.service;

import com.life_manager.identity_service.auth.application.dto.request.CreateUserRequest;
import com.life_manager.identity_service.auth.application.dto.request.UpdateUserRequest;
import com.life_manager.identity_service.auth.application.dto.response.PermissionResponse;
import com.life_manager.identity_service.auth.application.dto.response.RoleResponse;
import com.life_manager.identity_service.auth.application.dto.response.UserResponse;
import com.life_manager.identity_service.auth.application.mapper.UserMapper;
import com.life_manager.identity_service.auth.domain.entity.*;
import com.life_manager.identity_service.auth.domain.repo.RoleRepository;
import com.life_manager.identity_service.auth.domain.repo.UserRepository;
import com.life_manager.identity_service.core.exception.AppException;
import com.life_manager.identity_service.core.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleService roleService;
    RoleRepository roleRepository;

    @Transactional
    public UserResponse createUser(CreateUserRequest createUserRequest){
        if (userRepository.existsByUsername(createUserRequest.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        UserEntity user = userMapper.toUser(createUserRequest);
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        UserEntity userEntity = userRepository.save(user);
        roleService.assignDefaultRoles(userEntity);

        return userMapper.toUserResponse(userEntity);
    }

    @Transactional
    public UserResponse updateUser(String id, UpdateUserRequest updateUserRequest){
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED));
        userMapper.updateUser(user, updateUserRequest);
        user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));

        // ===== UPDATE ROLES =====
        if (updateUserRequest.getRoles() != null) {

            // clear old roles (orphanRemoval sẽ delete DB)
            user.getRoles().clear();
            userRepository.flush();

            // load RoleEntity
            Set<RoleEntity> roleEntities = roleRepository.findByRoleIn(updateUserRequest.getRoles());

            List<UserRoleEntity> userRoles = roleEntities.stream()
                    .map(role -> {
                        UserRoleEntity ur = new UserRoleEntity();
                        ur.setUser(user);
                        ur.setRole(role);
                        return ur;
                    })
                    .toList();

            user.getRoles().addAll(userRoles);
        }

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PostAuthorize("returnObject.username == authentication.name")
    @Transactional(readOnly = true)
    public UserResponse getUser(String id) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // Map basic fields bằng MapStruct
        UserResponse response = userMapper.toUserResponse(user);

        // Map roles manually
        Set<RoleResponse> roleResponses = user.getRoles().stream()
                .map(UserRoleEntity::getRole)
                .map(role -> {

                    Set<PermissionResponse> permissionResponses =
                            role.getRolePermissions().stream()
                                    .map(RolePermissionEntity::getPermission)
                                    .map(p -> PermissionResponse.builder()
                                            .name(p.getPermission().name())
                                            .description(p.getDescription())
                                            .build())
                                    .collect(Collectors.toSet());

                    return RoleResponse.builder()
                            .role(role.getRole())
                            .permissions(permissionResponses)
                            .build();
                })
                .collect(Collectors.toSet());

        response.setRoles(roleResponses);

        return response;
    }



    //    @PreAuthorize("#id == authentication.name or hasRole('ADMIN')")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUser() {

        List<UserEntity> users = userRepository.findAllWithRolesAndPermissions();

        return users.stream().map(user -> {

            Set<RoleResponse> roleResponses = user.getRoles().stream()
                    .map(UserRoleEntity::getRole)
                    .map(role -> {

                        Set<PermissionResponse> permissions = role.getRolePermissions().stream()
                                .map(RolePermissionEntity::getPermission)
                                .map(p -> PermissionResponse.builder()
                                        .name(p.getPermission().name())
                                        .description(p.getDescription())
                                        .build())
                                .collect(Collectors.toSet());

                        return RoleResponse.builder()
                                .role(role.getRole())
                                .permissions(permissions)
                                .build();
                    })
                    .collect(Collectors.toSet());

            return UserResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .dob(user.getDob())
                    .roles(roleResponses)
                    .build();
        }).toList();
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext().getAuthentication();
        String name = context.getName();
        UserEntity user = userRepository.findByUsername(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }
}
