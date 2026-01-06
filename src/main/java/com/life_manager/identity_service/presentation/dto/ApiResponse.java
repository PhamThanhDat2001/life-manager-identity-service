package com.life_manager.identity_service.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jspecify.annotations.NullMarked;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int code = 1000;

    private String message;
    private T result;

    private Instant timestamp = Instant.now();
}