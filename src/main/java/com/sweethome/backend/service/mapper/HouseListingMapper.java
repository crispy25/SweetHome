package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.HouseListing;
import com.sweethome.backend.service.dto.HouseListingDto;
import com.sweethome.backend.service.dto.HouseListingResponseDto;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class HouseListingMapper extends PropertyListingMapper<HouseListing, HouseListingDto, HouseListingResponseDto> {

    public abstract HouseListingResponseDto toDto(@NotNull HouseListing entity);

    public abstract HouseListing toEntity(@NotNull HouseListingDto dto);

    public abstract void updateEntity(@MappingTarget HouseListing houseListing, HouseListingDto dto);
}
