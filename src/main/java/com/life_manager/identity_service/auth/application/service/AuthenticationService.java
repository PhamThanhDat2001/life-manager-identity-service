package com.life_manager.identity_service.auth.application.service;

import com.life_manager.identity_service.auth.application.dto.request.AuthenticationRequest;
import com.life_manager.identity_service.auth.infrastructure.UserEntity;
import com.life_manager.identity_service.auth.infrastructure.UserJpaRepository;
import com.life_manager.identity_service.core.exeption.AppException;
import com.life_manager.identity_service.core.exeption.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserJpaRepository userJpaRepository;
    public boolean isAuthenticated(AuthenticationRequest authenticationRequest) {

        UserEntity user = userJpaRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());

    }
}
