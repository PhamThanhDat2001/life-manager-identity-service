package com.life_manager.identity_service.auth.application;

import com.life_manager.identity_service.auth.application.dto.CreateUserRequest;
import com.life_manager.identity_service.auth.infrastructure.UserEntity;
import com.life_manager.identity_service.auth.infrastructure.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserJpaRepository userJpaRepository;

    public UserEntity createUser(CreateUserRequest createUserRequest){
        UserEntity user = new UserEntity();
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(createUserRequest.getPassword());
        user.setLastName(createUserRequest.getLastName());
        user.setFirstName(createUserRequest.getFirstName());
        user.setDob(createUserRequest.getDob());
        return userJpaRepository.save(user);
    }
}
