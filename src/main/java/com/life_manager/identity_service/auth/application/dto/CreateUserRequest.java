package com.life_manager.identity_service.auth.application.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateUserRequest {
    private String username;
    
    @Size(min = 8, max = 50, message = "Password must be at least 8 character")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
