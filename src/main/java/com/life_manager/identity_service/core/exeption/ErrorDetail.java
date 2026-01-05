package com.life_manager.identity_service.core.exeption;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class ErrorDetail {
    private Instant timestamp;
    private String message;
    private String status;
}
