package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.HouseListing;
import com.sweethome.backend.service.dto.HouseListingDto;
import com.sweethome.backend.service.dto.HouseListingPatchDto;
import com.sweethome.backend.service.dto.HouseListingResponseDto;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class HouseListingMapper extends PropertyListingMapper<HouseListing, HouseListingDto, HouseListingPatchDto, HouseListingResponseDto> {

    public abstract HouseListingResponseDto toDto(@NotNull HouseListing entity);

    public abstract HouseListing toEntity(@NotNull HouseListingDto dto);

    public abstract void updateEntity(@MappingTarget HouseListing houseListing, HouseListingDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void patchEntity(@MappingTarget HouseListing houseListing, HouseListingPatchDto dto);
}
