package com.life_manager.identity_service.auth.application.service;

import com.life_manager.identity_service.auth.application.dto.request.AuthenticationRequest;
import com.life_manager.identity_service.auth.application.dto.request.IntrospectRequest;
import com.life_manager.identity_service.auth.application.dto.response.AuthenticationResponse;
import com.life_manager.identity_service.auth.application.dto.response.IntrospectResponse;
import com.life_manager.identity_service.auth.infrastructure.UserEntity;
import com.life_manager.identity_service.auth.infrastructure.UserJpaRepository;
import com.life_manager.identity_service.core.exeption.AppException;
import com.life_manager.identity_service.core.exeption.ErrorCode;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserJpaRepository userJpaRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNED_KEY;
    public AuthenticationResponse isAuthenticated(AuthenticationRequest authenticationRequest) {

        UserEntity user = userJpaRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
       var token = generateToken(authenticationRequest.getUsername());

        return AuthenticationResponse.builder().token(token).build();
    }

    private String generateToken(String username) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer(username)
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("userId","Custom")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNED_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

    }

    public IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        String token = introspectRequest.getToken();
        JWSVerifier jwsVerifier = new MACVerifier(SIGNED_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(jwsVerifier);

        return IntrospectResponse.builder()
                .valid(verified && expityTime.after(new Date())).build();

    }
}
