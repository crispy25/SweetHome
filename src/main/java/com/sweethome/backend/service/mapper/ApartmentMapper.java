package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.Apartment;
import com.sweethome.backend.service.dto.ApartmentDto;
import com.sweethome.backend.service.dto.ApartmentPatchDto;
import com.sweethome.backend.service.dto.ApartmentResponseDto;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ApartmentMapper {

    public abstract ApartmentResponseDto toDto(@NotNull Apartment entity);

    public abstract Apartment toEntity(@NotNull ApartmentDto dto);

    public abstract void updateEntity(@MappingTarget Apartment houseListing, ApartmentDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void patchEntity(@MappingTarget Apartment houseListing, ApartmentPatchDto dto);
}
