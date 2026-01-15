package com.life_manager.identity_service.auth.presentation;

import com.life_manager.identity_service.auth.application.service.UserService;
import com.life_manager.identity_service.auth.application.dto.CreateUserRequest;
import com.life_manager.identity_service.auth.infrastructure.UserEntity;
import com.life_manager.identity_service.core.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ApiResponse<UserEntity> createUser(@RequestBody @Valid CreateUserRequest createUserRequest){
        ApiResponse<UserEntity> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(createUserRequest));
        return apiResponse;
    }
}
