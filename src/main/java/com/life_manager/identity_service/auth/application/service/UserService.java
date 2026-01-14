package com.life_manager.identity_service.auth.application.service;

import com.life_manager.identity_service.auth.application.dto.CreateUserRequest;
import com.life_manager.identity_service.auth.application.mapper.UserMapper;
import com.life_manager.identity_service.auth.infrastructure.UserEntity;
import com.life_manager.identity_service.auth.infrastructure.UserJpaRepository;
import com.life_manager.identity_service.core.exeption.AppException;
import com.life_manager.identity_service.core.exeption.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;


    public UserEntity createUser(CreateUserRequest createUserRequest){
        if (userJpaRepository.existsByUsername(createUserRequest.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        UserEntity user = userMapper.toUser(createUserRequest);

//        user.setUsername(createUserRequest.getUsername());
//        user.setPassword(createUserRequest.getPassword());
//        user.setLastName(createUserRequest.getLastName());
//        user.setFirstName(createUserRequest.getFirstName());
//        user.setDob(createUserRequest.getDob());
        return userJpaRepository.save(user);
    }

}
