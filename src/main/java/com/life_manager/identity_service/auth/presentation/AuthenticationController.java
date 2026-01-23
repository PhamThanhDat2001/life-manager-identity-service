package com.life_manager.identity_service.auth.presentation;

import com.life_manager.identity_service.auth.application.dto.request.AuthenticationRequest;
import com.life_manager.identity_service.auth.application.dto.response.AuthenticationResponse;
import com.life_manager.identity_service.auth.application.service.AuthenticationService;
import com.life_manager.identity_service.core.dto.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> logIn(@RequestBody AuthenticationRequest authenticationRequest){

      boolean authenticated =  authenticationService.isAuthenticated(authenticationRequest);

    return ApiResponse.<AuthenticationResponse>builder().result(AuthenticationResponse.builder().authenticated(authenticated).build()).build();
    }
}
