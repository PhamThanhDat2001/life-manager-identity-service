package com.life_manager.identity_service.auth.application.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateRoleRequest {
    String name;
    String description;
    Set<String> permissions;
}
