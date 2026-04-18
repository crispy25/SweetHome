package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.User;
import com.sweethome.backend.service.dto.UserDto;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = RoleMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

    public abstract UserDto toDto(@NotNull User entity);
}
