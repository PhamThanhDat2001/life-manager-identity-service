package com.life_manager.identity_service.auth.presentation;

import com.life_manager.identity_service.auth.application.dto.request.UpdateUserRequest;
import com.life_manager.identity_service.auth.application.dto.response.UserResponse;
import com.life_manager.identity_service.auth.application.service.UserService;
import com.life_manager.identity_service.auth.application.dto.request.CreateUserRequest;
import com.life_manager.identity_service.core.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping()
    public ApiResponse<List<UserResponse>> getAll(){
        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getAllUser());
        return apiResponse;
    }

    @GetMapping("/my-info")
    public ApiResponse<UserResponse> getMyInfo(){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getMyInfo());
        return apiResponse;
    }
}
