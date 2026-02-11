package com.life_manager.identity_service.auth.presentation;

import com.life_manager.identity_service.auth.application.dto.request.CreatePermissionRequest;
import com.life_manager.identity_service.auth.application.dto.response.PermissionResponse;
import com.life_manager.identity_service.auth.application.service.PermissionService;
import com.life_manager.identity_service.core.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    public ApiResponse<PermissionResponse> createPermission(@RequestBody CreatePermissionRequest request){
        return ApiResponse.fromData(permissionService.createPermission(request));
    }

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getAllPermissions(){
        return ApiResponse.fromData(permissionService.getAllPermission());
    }
}
