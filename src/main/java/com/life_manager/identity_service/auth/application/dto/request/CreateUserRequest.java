package com.life_manager.identity_service.auth.application.dto.request;

import com.life_manager.identity_service.core.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @Size(min = 1, max = 50, message = "USERNAME_INVALID")
    private String username;
    
    @Size(min = 8, max = 50, message = "PASSWORD_INVALID")
    private String password;
    private String firstName;
    private String lastName;

    @DobConstraint(min = 15, message = "INVALID_DOB")
    private LocalDate dob;
}
