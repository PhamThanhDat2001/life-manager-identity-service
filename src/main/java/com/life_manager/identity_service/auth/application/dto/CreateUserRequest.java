package com.life_manager.identity_service.auth.application.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateUserRequest {
    @Size(min = 1, max = 50, message = "USERNAME_INVALID")
    private String username;
    
    @Size(min = 8, max = 50, message = "PASSWORD_INVALID")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
