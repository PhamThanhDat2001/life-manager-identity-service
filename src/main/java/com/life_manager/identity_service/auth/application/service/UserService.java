package com.life_manager.identity_service.auth.application.service;

import com.life_manager.identity_service.auth.application.dto.request.CreateUserRequest;
import com.life_manager.identity_service.auth.application.dto.request.UpdateUserRequest;
import com.life_manager.identity_service.auth.application.dto.response.UserResponse;
import com.life_manager.identity_service.auth.application.mapper.UserMapper;
import com.life_manager.identity_service.auth.domain.UserRepository;
import com.life_manager.identity_service.auth.domain.UserEntity;
import com.life_manager.identity_service.core.exeption.AppException;
import com.life_manager.identity_service.core.exeption.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleService roleService;

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

    public UserResponse updateUser(String id, UpdateUserRequest updateUserRequest){
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED));
        userMapper.updateUser(user, updateUserRequest);
        user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    public List<UserResponse> getAllUser() {
        return userMapper.toListUserResponse(userRepository.findAll());
    }
}
