package com.life_manager.identity_service.auth.application.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateUserRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
