package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.User;
import com.sweethome.backend.service.dto.UserDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(@NotNull User entity) {
        final UserDto dto = new UserDto();

        dto.setId(entity.getId())
                .setUsername(entity.getUsername())
                .setEmail(entity.getEmail());

        return dto;
    }
}
