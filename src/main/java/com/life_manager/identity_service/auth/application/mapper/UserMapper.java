package com.life_manager.identity_service.auth.application.mapper;
import com.life_manager.identity_service.auth.application.dto.request.CreateUserRequest;
import com.life_manager.identity_service.auth.application.dto.request.UpdateUserRequest;
import com.life_manager.identity_service.auth.application.dto.response.UserResponse;
import com.life_manager.identity_service.auth.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring") // báo cho maptruct biết là khai báo cái này để dùng trong spring như kiểu di
public interface UserMapper {
    UserEntity toUser(CreateUserRequest createUserRequest);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget UserEntity user, UpdateUserRequest updateUserRequest);

    @Mapping(target = "roles", ignore = true)
    UserResponse toUserResponse(UserEntity user);

    List<UserResponse> toListUserResponse(List<UserEntity> user);
}
