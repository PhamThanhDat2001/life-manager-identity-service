package com.life_manager.identity_service.auth.application.dto.entity;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Getter
public class UserDto {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Set<RoleDto> roles;
}
