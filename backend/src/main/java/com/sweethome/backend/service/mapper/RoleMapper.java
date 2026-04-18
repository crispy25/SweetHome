package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    default String map(Role role) {
        return role.getName();
    }
}