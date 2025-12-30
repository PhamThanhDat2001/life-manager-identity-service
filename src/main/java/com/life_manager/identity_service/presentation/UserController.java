package com.life_manager.identity_service.presentation;

import com.life_manager.identity_service.auth.application.UserService;
import com.life_manager.identity_service.auth.application.dto.CreateUserRequest;
import com.life_manager.identity_service.auth.infrastructure.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserEntity createUser(@RequestBody CreateUserRequest createUserRequest){
        return userService.createUser(createUserRequest);
    }
}
