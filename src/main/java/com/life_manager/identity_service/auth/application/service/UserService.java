package com.life_manager.identity_service.auth.application.service;

import com.life_manager.identity_service.auth.application.dto.request.CreateUserRequest;
import com.life_manager.identity_service.auth.application.dto.request.UpdateUserRequest;
import com.life_manager.identity_service.auth.application.dto.response.UserResponse;
import com.life_manager.identity_service.auth.application.mapper.UserMapper;
import com.life_manager.identity_service.auth.infrastructure.UserEntity;
import com.life_manager.identity_service.auth.infrastructure.UserJpaRepository;
import com.life_manager.identity_service.core.exeption.AppException;
import com.life_manager.identity_service.core.exeption.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserJpaRepository userJpaRepository;
    UserMapper userMapper;
//    PasswordEncoder passwordEncoder;

    public UserResponse createUser(CreateUserRequest createUserRequest){
        if (userJpaRepository.existsByUsername(createUserRequest.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        UserEntity user = userMapper.toUser(createUserRequest);
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));

        return userMapper.toUserResponse(userJpaRepository.save(user));
    }

    public UserResponse updateUser(String id, UpdateUserRequest updateUserRequest){
        UserEntity user = userJpaRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED));
        userMapper.updateUser(user, updateUserRequest);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));

        return userMapper.toUserResponse(userJpaRepository.save(user));
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(
                userJpaRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }
}
