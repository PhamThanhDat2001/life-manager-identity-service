package com.life_manager.identity_service.core.exeption;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    USERNAME_INVALID(1003, "Username is invalid"),
    PASSWORD_INVALID(1003, "Password is invalid"),
    USER_EXISTED(1001,"User existed"),
    INVALID_KEY(1003, "Uncategorized error");

    private final int code;
    private final String message;
}
