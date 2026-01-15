package com.life_manager.identity_service.auth.presentation;

import com.life_manager.identity_service.auth.application.dto.UpdateUserRequest;
import com.life_manager.identity_service.auth.application.dto.UserResponse;
import com.life_manager.identity_service.auth.application.service.UserService;
import com.life_manager.identity_service.auth.application.dto.CreateUserRequest;
import com.life_manager.identity_service.auth.infrastructure.UserEntity;
import com.life_manager.identity_service.core.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid CreateUserRequest createUserRequest){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(createUserRequest));
        return apiResponse;
    }

    @PatchMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody @Valid UpdateUserRequest updateUserRequest){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUser(id, updateUserRequest));
        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable String id){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUser(id));
        return apiResponse;
    }
}
