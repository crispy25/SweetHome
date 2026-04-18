package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.House;
import com.sweethome.backend.service.dto.HouseDto;
import com.sweethome.backend.service.dto.HousePatchDto;
import com.sweethome.backend.service.dto.HouseResponseDto;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = RoleMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class HouseMapper {

    public abstract HouseResponseDto toDto(@NotNull House entity);

    public abstract House toEntity(@NotNull HouseDto dto);

    public abstract void updateEntity(@MappingTarget House houseListing, HouseDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void patchEntity(@MappingTarget House houseListing, HousePatchDto dto);
}
