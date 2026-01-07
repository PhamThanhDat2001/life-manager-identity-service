package com.life_manager.identity_service.auth.application.dto;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    @Size(min = 1, max = 50, message = "USERNAME_INVALID")
     String username;
    
    @Size(min = 8, max = 50, message = "PASSWORD_INVALID")
     String password;
     String firstName;
     String lastName;
     LocalDate dob;
}
