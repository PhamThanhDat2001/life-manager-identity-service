package com.life_manager.identity_service.auth.application.mapper;
import com.life_manager.identity_service.auth.application.dto.CreateUserRequest;
import com.life_manager.identity_service.auth.infrastructure.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // báo cho maptruct biết là khai báo cái này để dùng trong spring như kiểu di
public interface UserMapper {
    UserEntity toUser(CreateUserRequest createUserRequest);
}
