package com.life_manager.identity_service.auth.presentation;

import com.life_manager.identity_service.auth.application.dto.request.CreateRoleRequest;
import com.life_manager.identity_service.auth.application.dto.response.RoleResponse;
import com.life_manager.identity_service.auth.application.service.RoleService;
import com.life_manager.identity_service.core.dto.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/roles")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody CreateRoleRequest request) {
        return ApiResponse.fromData(roleService.createRole(request));
    }
//
//    // READ ONE
//    @GetMapping("/{id}")
//    public ApiResponse<RoleResponse> getRole(@PathVariable Long id) {
//        return ApiResponse.fromData(roleService.getRole(id));
//    }
//
//    // READ ALL
    @GetMapping
    public ApiResponse<List<RoleResponse>> getAllRoles() {
        return ApiResponse.fromData(roleService.getAllRoles());
    }
//
//    // UPDATE
//    @PutMapping("/{id}")
//    public ApiResponse<RoleResponse> updateRole(
//            @PathVariable Long id,
//            @RequestBody CreateRoleRequest request) {
//
//        return ApiResponse.fromData(roleService.updateRole(id, request));
//    }
//
//    // DELETE
//    @DeleteMapping("/{id}")
//    public ApiResponse<Void> deleteRole(@PathVariable Long id) {
//        roleService.deleteRole(id);
//        return ApiResponse.fromData(null);
//    }
}
