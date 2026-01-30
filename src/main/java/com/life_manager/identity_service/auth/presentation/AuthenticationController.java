package com.life_manager.identity_service.auth.presentation;

import com.life_manager.identity_service.auth.application.dto.request.AuthenticationRequest;
import com.life_manager.identity_service.auth.application.dto.request.IntrospectRequest;
import com.life_manager.identity_service.auth.application.dto.response.AuthenticationResponse;
import com.life_manager.identity_service.auth.application.dto.response.IntrospectResponse;
import com.life_manager.identity_service.auth.application.service.AuthenticationService;
import com.life_manager.identity_service.core.dto.ApiResponse;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> logIn(@RequestBody AuthenticationRequest authenticationRequest){

        AuthenticationResponse authenticated =  authenticationService.isAuthenticated(authenticationRequest);

    return ApiResponse.<AuthenticationResponse>builder().result(authenticated).build();
    }
    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {

    IntrospectResponse introspect =  authenticationService.introspect(introspectRequest);

    return ApiResponse.<IntrospectResponse>builder().
            result(introspect)
            .build();
    }
}
