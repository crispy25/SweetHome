package com.sweethome.backend.service.mapper;

import com.sweethome.backend.entity.ApartmentListing;
import com.sweethome.backend.service.dto.ApartmentListingDto;
import com.sweethome.backend.service.dto.ApartmentListingResponseDto;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ApartmentListingMapper extends PropertyListingMapper<ApartmentListing, ApartmentListingDto, ApartmentListingResponseDto> {

    public abstract ApartmentListingResponseDto toDto(@NotNull ApartmentListing entity);

    public abstract ApartmentListing toEntity(@NotNull ApartmentListingDto dto);

    public abstract void updateEntity(@MappingTarget ApartmentListing houseListing, ApartmentListingDto dto);
}
